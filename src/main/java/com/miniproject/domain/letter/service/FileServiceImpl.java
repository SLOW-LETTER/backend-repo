package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.File;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    FileRepository repository;

    public Result createFile(FileDto fileDto) {
        File file = fileDto.toEntity();
        repository.save(file);
        Result result = new Result();
        result.setPayload(file);
        return result;
    }

    public Result retrieveFileList() {
        List<File> list = repository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    // 저장된 letter_id에 일치하는 file 찾기
    public Result retrieveLetterIdFile(int letterId) {
        Optional<File> optionalFile = repository.findAllByOrderByLetterIdDesc(letterId);
        Result result = new Result();
        result.setPayload(optionalFile.get().getFileUrl());
        return result;
    }

    // 파일 삭제
    public Result deleteFile(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if (!isPresent) {
            result.setMessage(ErrorCode.PA02);
        } else {
            repository.deleteById(id);
        }
        return result;
    }
}
