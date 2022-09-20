package com.miniproject.global.error.service;

import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import lombok.Getter;

@Getter

public class CustomException extends  RuntimeException {
    private final Result result;

    public CustomException(Result result){
        this.result = result;
    }
}
