package kr.kro.ddalkak.project.global.exception;

import kr.kro.ddalkak.project.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException {


    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
