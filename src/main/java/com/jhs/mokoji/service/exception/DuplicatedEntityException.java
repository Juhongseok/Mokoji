package com.jhs.mokoji.service.exception;

import com.jhs.mokoji.controller.response.error.ErrorCode;

public class DuplicatedEntityException extends BusinessException {
    public DuplicatedEntityException(ErrorCode errorCode){
        super(errorCode);
    }
}