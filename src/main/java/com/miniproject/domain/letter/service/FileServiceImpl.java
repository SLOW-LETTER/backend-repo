package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.File;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.letter.service
 * @name : FileServiceImpl
 * @create-date: 2022.09.30
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService{
    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public FileServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Result createFile(FileDto fileDto, @RequestHeader("X-AUTH-TOKEN") String token) {
        File file = fileDto.toEntity();
        //User user = new User();
        String senderEmail = jwtTokenProvider.getUserPk(token);
        Optional<User> senderUser = userRepository.findByEmail(senderEmail);
        log.info(String.valueOf(senderUser.get().getId()));
        file.setSenderId(senderUser.get().getId());
        fileRepository.save(file);
        Result result = new Result();
        result.setPayload(file.getId());
        return result;
    }

    public Result retrieveFileList() {
        List<File> list = fileRepository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    // 저장된 letter_id에 일치하는 file 찾기
    public Result retrieveLetterIdFile(int letterId) {
        Optional<File> optionalFile = fileRepository.findAllByOrderByLetterIdDesc(letterId);
        Result result = new Result();
        result.setPayload(optionalFile.get().getFileUrl());
        return result;
    }

    // 파일 삭제
    public Result deleteFile(int id) {
        Result result = new Result();
        boolean isPresent = fileRepository.findById(id).isPresent();
        if (!isPresent) {
            result.setMessage(ErrorCode.PA02);
        } else {
            fileRepository.deleteById(id);
        }
        return result;
    }
}
