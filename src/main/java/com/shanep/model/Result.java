package com.shanep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private ErrorResponse error;
	private Object payload;
	private Object templates;
	private String message;
}