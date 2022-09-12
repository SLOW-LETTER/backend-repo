package com.miniproject.global.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private ErrorResponse message;
	private Object payload;
}
