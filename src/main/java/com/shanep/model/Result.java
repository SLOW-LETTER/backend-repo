package com.shanep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private ErrorResponse message;
	private Object payload;
}
