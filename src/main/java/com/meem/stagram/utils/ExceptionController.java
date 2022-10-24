package com.meem.stagram.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * 설명 : ExceptionController.java 
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.24    김요한    최초작성  (ControllerAdvice , MethodArgumentNotValidException )
 * -------------------------------------------------------------
 */

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ExceptionResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return buildFieldErrors(ExceptionCode.INPUT_VALUE_INVALID, fieldErrors);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ExceptionResponse handleBindException(org.springframework.validation.BindException e) {
        final List<ExceptionResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return buildFieldErrors(ExceptionCode.INPUT_VALUE_INVALID, fieldErrors);
    }


    private List<ExceptionResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        
        return errors.parallelStream()
                .map(error -> ExceptionResponse.FieldError.builder()
                        .i_result_msg(error.getDefaultMessage())
                        .i_input_id(error.getField())
                        .i_input_value((String) error.getRejectedValue())
                        .i_result_cd("ERROR")
                        .build())
                .collect(Collectors.toList());
    }

    private ExceptionResponse buildFieldErrors(ExceptionCode errorCode, List<ExceptionResponse.FieldError> errors) {
        
        return ExceptionResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }
}