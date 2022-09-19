package com.miniproject.global.entity;

import com.miniproject.global.enumpkg.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @package : com.shanep.exception
 * @name : ErrorResponse
 * @create-date: 2022.09.17
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

	private String code;
	private String message;
	private int status;
	
	public ErrorResponse(ErrorCode code) {
		this.message = code.getMessage();
		this.status = code.getStatus();
		this.code = code.getCode();
	}

	public static ErrorResponse of(ErrorCode code) {
		return new ErrorResponse(code);
	}
	
}
