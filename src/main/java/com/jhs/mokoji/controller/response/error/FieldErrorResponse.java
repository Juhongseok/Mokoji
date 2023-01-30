package com.jhs.mokoji.controller.response.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class FieldErrorResponse {
    private String field;
    private String value;
    private String message;

    private FieldErrorResponse(FieldError fieldError) {
        this.field = fieldError.getField();
        this.value = fieldError.getRejectedValue().toString();
        this.message = fieldError.getDefaultMessage();
    }

    public static List<FieldErrorResponse> of(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(FieldErrorResponse::new)
                .collect(toList());
    }
}