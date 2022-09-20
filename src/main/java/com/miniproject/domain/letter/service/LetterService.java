package com.miniproject.domain.letter.service;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.global.entity.Result;


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
    public Result createLetter(Letter letter, FileDto fileDto, UserDto userDto);

    public Result retrieveReceiver(int id);

    public Result retrieveSender();

    public Result retrieveLetter(int id);

    public Result deleteLetter(int id);
}