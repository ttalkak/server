package com.ttalkak.deployment.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 배포
    NOT_EXISTS_DEPLOYMENT(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 배포 내역이 존재하지 않습니다"),

    // 호스팅
    NOT_EXISTS_HOSTING(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "호스팅 내역이 존재하지 않습니다")


    ;

    private final HttpStatus httpStatus;
    private final Integer statusCode;
    private final String message;
}
