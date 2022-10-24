package com.meem.stagram.utils;

import lombok.Getter;

/**
 * 설명 : ExceptionCode.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.24    김요한    최초작성 
 * -------------------------------------------------------------
 */

@Getter
public enum ExceptionCode {
    INPUT_VALUE_INVALID("ERROR_CODE_9999", "입력값이 올바르지 않습니다.", 400);
    /*NOT_EXCEPTION("ERROR_CODE_9999", "유효성검증 실패", 400)
    , NOT_NULL("ERROR_CODE_0001","필수값이 누락되었습니다.", 400)
    , NOT_BLANK("ERROR_CODE_0002", "필수값이 누락되었습니다.", 400)
    , EMAIL_VALUE("ERROR_CODE_0003", "이메일형식이 올바르지 않습니다.", 400)
    , MIN_VALUE("ERROR_CODE_0004", "최소값보다 커야 합니다.", 400)
    ;*/

    private final String code;
    private final String message;
    private final int status;

    ExceptionCode(String i_code, String i_message, int i_status) {
        this.code = i_code;
        this.message = i_message;
        this.status = i_status;
    }
}