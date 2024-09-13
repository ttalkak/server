package com.ttalkak.notification.notification.application.port.`in`

interface ConfirmCodeUseCase {
    /**
     * 인증 코드를 확인한다.
     *
     * @param email 이메일
     * @param code 인증 코드
     */
    fun confirmCode(email: String, code: String)
}