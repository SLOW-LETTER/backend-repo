package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.dto.LetterDto;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.global.entity.Result;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @package : com.miniproject.domain.letter.service
 * @name : LetterService
 * @create-date: 2022.09.15
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

public interface LetterService {
    public Result createLetter(LetterDto letterDto, FileDto fileDto, UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token);

    // 편지 보낸 사람 조회 (유저가 보낸 편지들 조회)
    public Result retrieveSenderList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token);

    // 편지 받는 사람 조회 (유저가 받은 편지들 조회)
    public Result retrieveReceiverList(UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token);

    // 편지 내용 조회
    public Result retrieveLetter(int id);

    public Result deleteLetter(int id);
}
