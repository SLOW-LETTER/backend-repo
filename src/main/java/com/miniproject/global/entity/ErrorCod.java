package com.miniproject.global.entity;

import com.miniproject.global.enumpkg.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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
public class ErrorCod {

	private String code;
	private String message;
	private HttpStatus status;
	
	public ErrorCod(ErrorCode code) {
		this.message = code.getMessage();
		this.status = code.getStatus();
		this.code = code.getCode();
	}

	public static ErrorCod of(ErrorCode code) {
		return new ErrorCod(code);
	}
	
}
