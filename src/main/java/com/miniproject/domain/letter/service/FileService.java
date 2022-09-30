package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.global.entity.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.letter.service
 * @name : FileService
 * @create-date: 2022.09.30
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Service
public interface FileService {
    // 파일 생성
    public Result createFile(FileDto fileDto, @RequestHeader("X-AUTH-TOKEN") String token);

    // 파일 조회
    public Result retrieveFileList();

    // 저장된 letter_id에 일치하는 file 찾기
    public Result retrieveLetterIdFile(int letterId);

    // 파일 삭제
    public Result deleteFile(int id);
}
