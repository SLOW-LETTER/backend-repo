package com.miniproject.global.error.service;

import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Result>
    handleCustomException(CustomException e){
        log.error("handleCustomException",e);
        ErrorResponse response = new ErrorResponse(e.getResult().getMessage());
        return new ResponseEntity<>(
                e.getResult(), HttpStatus.valueOf(e.getResult().getMessage().getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleException(Exception e){
        log.error("handleException", e);
        ErrorResponse response = new ErrorResponse(ErrorCode.RE01);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
