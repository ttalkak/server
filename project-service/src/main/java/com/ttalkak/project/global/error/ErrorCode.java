package com.ttalkak.project.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 프로젝트
    NOT_EXISTS_PROJECT(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "해당 프로젝트는 존재하지 않습니다")
    ;

    private final HttpStatus status;
    private final Integer statusCode;
    private final String message;
}
