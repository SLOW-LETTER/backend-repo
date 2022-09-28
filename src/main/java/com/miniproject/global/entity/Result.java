package com.miniproject.global.entity;

import com.miniproject.global.enumpkg.ErrorCode;
import com.miniproject.global.error.service.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Object payload;
    private ErrorCode message;
}
