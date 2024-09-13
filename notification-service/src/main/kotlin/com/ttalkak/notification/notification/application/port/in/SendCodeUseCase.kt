package com.ttalkak.notification.notification.application.port.`in`

interface SendCodeUseCase {
    /**
     * 이메일로 인증 코드를 전송한다.
     *
     * @param email 이메일
     */
    fun sendCode(email: String)
}