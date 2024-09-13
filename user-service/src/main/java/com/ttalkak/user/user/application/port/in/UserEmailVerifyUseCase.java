package com.ttalkak.user.user.application.port.in;

public interface UserEmailVerifyUseCase {
    /**
     * 이메일 인증 처리
     * 
     * @param email 이메일
     */
    void verifyEmail(String email);
}
