package com.meem.stagram.utils;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 설명 : ExceptionResponse.java
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.24    김요한    최초작성
 * -------------------------------------------------------------
 */

@Getter
public class ExceptionResponse {

    private String message;
    private String code;
    private int status;
    private List<FieldError> errors = new ArrayList<>();

    @Builder
    public ExceptionResponse(String message, String code, int status, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.errors = initErrors(errors);
    }

    private List<FieldError> initErrors(List<FieldError> errors) {
        return (errors == null) ? new ArrayList<>() : errors;
    }

    @Getter
    public static class FieldError {
        
        @NotBlank
        private String inputId;
        
        @NotBlank
        private String inputValue;
        
        @NotBlank
        private String resultCd;
        
        @NotBlank
        private String resultMsg;
        
        @Builder
        public FieldError(String i_input_id, String i_input_value, String i_result_cd, String i_result_msg) {
            this.inputId = i_input_id;
            this.inputValue = i_input_value;
            this.resultCd = i_result_cd;
            this.resultMsg = i_result_msg;
        }
    }
    
}