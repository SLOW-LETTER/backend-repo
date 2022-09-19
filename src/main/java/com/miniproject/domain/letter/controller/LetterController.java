package com.miniproject.domain.letter.controller;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.repository.LetterRepository;
import com.miniproject.domain.letter.service.LetterService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @package : com.miniproject.domain.letter.controller;
 * @name : LetterController
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@RestController
@RequestMapping(value = "/api/v1/letters")
@RequiredArgsConstructor
public class LetterController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LetterController.class);

    private final S3Service s3Service;

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    LetterService letterService;

//    @Autowired
//    FileService fileService;

    @PostMapping
    public Result createLetter(Letter letter, FileDto fileDto) throws IOException{
        //String id = Integer.toString(letter.getId()); // letter id 별로 버킷에 저장위한 형변환
        String url = s3Service.uploadFile(fileDto.getFile(), letter.getId()); // 추후에 뒤 dir 부분은 토큰 or 아이디로?
        fileDto.setUrl(url);
        //Result resultTest = fileService.createFile(fileDto);
        Result result = letterService.createLetter(letter, fileDto);
        return result;
    }

    @GetMapping("/sender")
    public Result retrieveSender(){
        Result result = letterService.retrieveSender();
        return result;
    }


    @DeleteMapping("/{id}")
    public Result deleteLetter(@PathVariable int id){
        Result result = letterService.deleteLetter(id);
        return result;
    }
}
