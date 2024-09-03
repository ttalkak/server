package com.ttalkak.project.global.exception;

import com.ttalkak.project.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException {


    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
