package com.miniproject.global.entity;

import com.miniproject.global.enumpkg.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

public class ExcpetionController {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ErrorCode> handleAuthenticationException(AuthenticationException ex){
        ErrorCode errorResponse = new ErrorCode(ex.)
    }
}
