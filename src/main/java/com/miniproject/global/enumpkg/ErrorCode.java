package com.miniproject.global.enumpkg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniproject.global.error.service.EnumModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : 원우연
 * @version : 1.0.0
 * @package : com.shanep.exception
 * @name : ErroCode
 * @create-date: 2022.09.17
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel {

    //요청 관련 에러(RE)
    RE01("invalid requirements error", "RE01", 404),
    RE02("wrong requirements error", "RE02", 404),

    //파라미터 관련 에러(PA)
    PA01("Invalid agreement_code: ${Agreement Code}", "PA01", 400),
    PA02("necessary parameter missing", "PA02", 400),
    PA03("wrong password error", "PA03", 400),
    PA04("invalid id error", "PA04", 400),

    //토큰 관련 에러(TO)
    TO01("refresh token is blank", "TO01", 401),
    TO02("used the same authorization code more than once, used an expired authorization code, or cannot find the authorization code", "TO02", 401),
    TO03("Refresh_token not found or already expired refresh token used", "TO03", 401),
    TO04("invalid token error", "TO04", 400),

    //서버 에러(SE)
    SE01("db server error", "SE01", 500),
    SE02("AWS server error", "SE02", 500),
    SE03(" A temporary error occurred on the OAuth server", "SE03", 500);

    private String message;
    private String code;

    private int status;

    @Override
    public String getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.message;
    }
}
