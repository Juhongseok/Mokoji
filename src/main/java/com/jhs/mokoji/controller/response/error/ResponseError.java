package com.jhs.mokoji.controller.response.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseError {
    private int statusCode;
    private String message;
    private List<FieldErrorResponse> error;

    private ResponseError(ErrorCode errorCode, List<FieldErrorResponse> error) {
        this.statusCode = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.error = error;
    }

    private ResponseError(ErrorCode errorCode) {
        this.statusCode = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.error = new ArrayList<>();
    }


    public static ResponseError of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ResponseError(errorCode, FieldErrorResponse.of(bindingResult));
    }

    public static ResponseError of(ErrorCode errorCode) {
        return new ResponseError(errorCode);
    }
}