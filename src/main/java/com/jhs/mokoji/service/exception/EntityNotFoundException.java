package com.jhs.mokoji.service.exception;

import com.jhs.mokoji.controller.response.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}