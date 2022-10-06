package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.dto.LetterDto;
import com.miniproject.domain.letter.entity.File;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.repository.LetterRepository;
import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.transportation.repository.TransportationRepository;
import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

/**
 * @package : com.miniproject.domain.letter.service;
 * @name : LetterServiceImpl
 * @create-date: 2022.09.15
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Service
@Slf4j
public class LetterServiceImpl implements LetterService{
    // receiver가 존재하는지 확인 예외처리 필요 (전체적으로)
    // 탈퇴한 유저와 관련된 편지 열람 관련 논의 필요 (전체적으로)
    // 위도 경도 관련 데이터 없음 (create에서 필요, 지구본에서 필요해서 현재는 구현 X)
    // 편지에 첨부하는 파일 여러개 구현
    @Autowired
    LetterRepository letterRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    TransportationRepository transportationRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public LetterServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Result createLetter(LetterDto letterDto, FileDto fileDto, UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token) {
        Letter letter = letterDto.toEntity();
        User user = new User();
        String senderEmail = jwtTokenProvider.getUserPk(token);
        Optional<User> senderUser = userRepository.findByEmail(senderEmail);
        letter.setSender(senderUser.get());
        Optional<User> receiverUser = userRepository.findByEmail(letter.getReceiverEmail());
        letter.setReceiver(receiverUser.get());
        Optional<Template> templateLetter = templateRepository.findById(letterDto.getTemplateId());
        letter.setTemplateId(templateLetter.get());
        Optional<Transportation> transportationLetter = transportationRepository.findById(letterDto.getTransportationId());
        letter.setTransportationId(transportationLetter.get());

        user.getLetters().add(letter);
        user.getReceiveLetters().add(letter);

        if(fileDto.getFile() != null) {
            File file = fileDto.toEntity();
            file.setLetter(letter);
            fileRepository.save(file);
        }

        letter = letterRepository.save(letter);

        Result result = new Result();
        result.setPayload(letter.getId());
        return result;
    }

    // 편지 보낸 사람 조회 (유저가 보낸 편지들 조회)
    public Result retrieveReceiverList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token) {
        String receiverEmail = jwtTokenProvider.getUserPk(token);
        Optional<User> receiverUser = userRepository.findByEmail(receiverEmail);
        List<Letter> receiverLetter = letterRepository.findAllByOrderByReceiverIdDesc(receiverUser.get().getId());
        JSONArray retrieveReceiverArray = new JSONArray();
        Result result = new Result();
        for(int i = 0; i<receiverLetter.size(); i++){
            JSONObject retrieveReceiverObj = new JSONObject();
            retrieveReceiverObj.put("_id", receiverLetter.get(i).getId()); // 편지 _id
            retrieveReceiverObj.put("sender", receiverLetter.get(i).getSender().getName());
            retrieveReceiverObj.put("reciever",receiverLetter.get(i).getReceiver().getName());
            retrieveReceiverObj.put("transportation", receiverLetter.get(i).getTransportationId().getName());
            retrieveReceiverObj.put("departureCountry", receiverLetter.get(i).getDepartureCountry());
            retrieveReceiverObj.put("departureCity", receiverLetter.get(i).getDepartureCity());
            retrieveReceiverObj.put("arrivalCountry", receiverLetter.get(i).getArrivalCountry());
            retrieveReceiverObj.put("arrivalCity", receiverLetter.get(i).getArrivalCity());
            retrieveReceiverObj.put("boardingTime", receiverLetter.get(i).getBoardingTime());
            retrieveReceiverObj.put("arrivalTime", receiverLetter.get(i).getArrivalTime());
            retrieveReceiverArray.add(retrieveReceiverObj);
            log.info((String) retrieveReceiverObj.get(i));
        }
        JSONObject obj = new JSONObject();
        obj.put("Letters" , retrieveReceiverArray);
        result.setPayload(obj);
        return result;
    }

    // 편지 보낸 사람 조회 (유저가 보낸 편지들 조회)
    public Result retrieveSenderList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token) {
        String senderEmail = jwtTokenProvider.getUserPk(token);
        Optional<User> senderUser = userRepository.findByEmail(senderEmail);
        List<Letter> senderLetter = letterRepository.findAllByOrderBySenderIdDesc(senderUser.get().getId());
        JSONArray retrieveSenderArray = new JSONArray();
        Result result = new Result();
        for(int i = 0; i<senderLetter.size(); i++){
            JSONObject retrieveSenderObj = new JSONObject();
            retrieveSenderObj.put("_id", senderLetter.get(i).getId()); // 편지 _id
            retrieveSenderObj.put("sender", senderLetter.get(i).getSender().getName());
            retrieveSenderObj.put("reciever",senderLetter.get(i).getReceiver().getName());
            retrieveSenderObj.put("transportation", senderLetter.get(i).getTransportationId().getName());
            retrieveSenderObj.put("departureCountry", senderLetter.get(i).getDepartureCountry());
            retrieveSenderObj.put("departureCity", senderLetter.get(i).getDepartureCity());
            retrieveSenderObj.put("arrivalCountry", senderLetter.get(i).getArrivalCountry());
            retrieveSenderObj.put("arrivalCity", senderLetter.get(i).getArrivalCity());
            retrieveSenderObj.put("boardingTime", senderLetter.get(i).getBoardingTime());
            retrieveSenderObj.put("arrivalTime", senderLetter.get(i).getArrivalTime());
            retrieveSenderArray.add(retrieveSenderObj);
            log.info((String) retrieveSenderObj.get(i));
        }
        JSONObject obj = new JSONObject();
        obj.put("Letters" , retrieveSenderArray);
        result.setPayload(obj);
        return result;
    }

    // 편지 내용 조회 (현재 file 부분 추가 필요)
    public Result retrieveLetter(int id) {
        Optional<Letter> optionLetter = letterRepository.findById(id);
        Optional<File> optionalFile = fileRepository.findAllByOrderByLetterIdDesc(id);
        Result result = new Result();
        JSONObject retrieveLetterObj = new JSONObject();
        if(optionLetter.isPresent()){
            if(optionLetter.get().isDeleted() == false){
                retrieveLetterObj.put("templateUrl", optionLetter.get().getTemplateId().getFileUrl());
                retrieveLetterObj.put("title", optionLetter.get().getTitle());
                retrieveLetterObj.put("content", optionLetter.get().getContent());
                retrieveLetterObj.put("departureCity", optionLetter.get().getDepartureCity());
                retrieveLetterObj.put("arrivalCity", optionLetter.get().getArrivalCity());
                retrieveLetterObj.put("transportation", optionLetter.get().getTransportationId().getName());
                retrieveLetterObj.put("fileUrl", optionalFile.get().getFileUrl());
                result.setPayload(retrieveLetterObj);
            }
            else{
                result.setMessage(ErrorCode.PA02);
            }
        }
        return result;
    }

    // 편지 삭제 > 프론트에서 사용 X
    public Result deleteLetter(int id) {
        Result result = new Result();
        boolean isPresent = letterRepository.findById(id).isPresent();
        if(!isPresent) {
            result.setMessage(ErrorCode.PA02);
        } else {
            letterRepository.deleteById(id);
        }
        return result;
    }
}
