package com.miniproject.global.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private Object payload;
    private ErrorResponse message;
}
