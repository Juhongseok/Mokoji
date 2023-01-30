package com.jhs.mokoji.controller.error;

import com.jhs.mokoji.controller.response.error.ErrorCode;
import com.jhs.mokoji.controller.response.error.ResponseError;
import com.jhs.mokoji.service.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ErrorAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseError MethodArgumentNotValidExceptionHandler(BindException e) {
        return ResponseError.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ResponseError BusinessExceptionHandler(BusinessException e) {
        return ResponseError.of(e.getErrorCode());
    }
}