package com.miniproject.domain.letter.controller;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.dto.LetterDto;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.repository.LetterRepository;
import com.miniproject.domain.letter.service.LetterService;
import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Letter", description = "letter api 입니다.")
public class LetterController {
    private final S3Service s3Service;

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    LetterService letterService;

    // 편지 생성, AWS 경로 수정 필요
    // DTO에도 스웨거에 맞게 수정 필요
    @Operation(summary = "Letter 생성", description = "Letter 생성 api입니다.")
    @PostMapping
    public Result createLetter(@ModelAttribute LetterDto letterDto, @ModelAttribute FileDto fileDto, @Parameter(hidden = true) UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token) throws IOException{
        Letter letter = letterDto.toEntity();
        //String id = Integer.toString(letter.getId()); // letter id 별로 버킷에 저장위한 형변환
        if(fileDto.getFile() != null) {
            String url = s3Service.uploadFile(fileDto.getFile(), letter.getId()); // 추후에 뒤 dir 부분은 토큰 or 아이디로?
            fileDto.setUrl(url);
        }
        //Result resultTest = fileService.createFile(fileDto);
        Result result = letterService.createLetter(letterDto, fileDto, userDto, token);
        return result;
    }

    // 편지 보낸 사람 조회 (유저가 보낸 편지들 조회)
    @GetMapping("/sender")
    public Result retrieveSenderList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.retrieveSenderList(userDto, token);
        return result;
    }

    // 편지 받는 사람 조회 (유저가 받은 편지들 조회)
    @GetMapping("/receiver")
    public Result retrieveReceiverList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.retrieveReceiverList(userDto,token);
        return result;
    }

    // 편지 내용 조회
    @GetMapping("/{id}")
    public Result retrieveLetter(@PathVariable int id){
        Result result = letterService.retrieveLetter(id);
        return result;
    }

    // 편지 삭제 > 프론트에서 사용 X
    @DeleteMapping("/{id}")
    public Result deleteLetter(@PathVariable int id){
        Result result = letterService.deleteLetter(id);
        return result;
    }
}
