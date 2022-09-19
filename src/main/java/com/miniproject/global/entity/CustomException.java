package com.miniproject.global.entity;

import com.miniproject.global.enumpkg.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @package : com.shanep.exception
 * @name : CustomException
 * @create-date: 2022.09.17
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@AllArgsConstructor
@Getter
public class CustomException {

    private ErrorCode errorCode;



}
