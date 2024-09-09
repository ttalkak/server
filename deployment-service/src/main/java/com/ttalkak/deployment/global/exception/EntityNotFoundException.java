package com.ttalkak.deployment.global.exception;

import com.ttalkak.deployment.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
