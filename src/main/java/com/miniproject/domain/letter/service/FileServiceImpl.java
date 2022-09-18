package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.File;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.global.entity.Result;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @package : com.miniproject.domain.letter.service;
 * @name : FileServiceImpl
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Service
public class FileServiceImpl implements FileService{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FileServiceImpl.class);

    @Autowired
    FileRepository repository;

    public Result createFile(FileDto fileDto) {
        File file = new File(fileDto.getFileName(), fileDto.getUrl());
        repository.save(file);
        Result result = new Result();
        result.setPayload(file);
        return result;
    }

    @Override
    public Result retrieveFileList() {
        List<File> list = repository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    @Override
    public Result deleteFile(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if (!isPresent) {
            // ??
        } else {
            repository.deleteById(id);
        }
        return result;
    }
}
