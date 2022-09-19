package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.File;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.repository.LetterRepository;
import com.miniproject.global.entity.Result;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LetterServiceImpl implements LetterService{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LetterServiceImpl.class);

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    FileRepository fileRepository;

    public Result createLetter(Letter letter, FileDto fileDto) {
        File file = new File(fileDto.getFileName(), fileDto.getUrl());
        fileRepository.save(file);
        letter = letterRepository.save(letter);
        Result result = new Result();
        result.setPayload(letter);
        //result.setMessage("편지 생성 완료");
        return result;
    }

    // 받는사람 조회
    public Result retrieveReceiver(int id) {
        return null;
    }

    // 보낸사람 조회
    public Result retrieveSender() {
        List<Letter> senderLetter = letterRepository.findAllByOrderByIdDesc();
        Result result = new Result();
//        if(optionLetter.isPresent()) {
//            result.setPayload(optionLetter.get());
//            //result.setMessage("편지 조회 성공");
//        } else {
//            //result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
//        }
        result.setPayload(senderLetter);
        return result;
    }

    @Override
    public Result retrieveLetter(int id) {
        return null;
    }

//    public Result retrieveLetter(int id) {
//        List<Letter> letter
//    }

    public Result deleteLetter(int id) {
        Result result = new Result();
        boolean isPresent = letterRepository.findById(id).isPresent();
        if(!isPresent) {
            //result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else {
            letterRepository.deleteById(id);
        }
        //result.setMessage("편지 삭제 성공");
        return result;
    }
}