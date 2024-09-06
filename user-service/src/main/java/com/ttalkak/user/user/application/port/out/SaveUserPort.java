package com.ttalkak.user.user.application.port.out;

import com.ttalkak.user.user.adapter.out.persistence.entity.UserEntity;

public interface SaveUserPort {
    /**
     * 사용자 정보를 저장한다. (
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     */
    UserEntity save(String username, String password, String email);

    /**
     * 사용자 정보를 저장한다. (OAuth2 사용자 전용 Provider)
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     * @param providerId OAuth2 제공자 ID
     * @return 저장된 사용자 정보
     */
    UserEntity save(String username, String password, String email, String providerId, String accessToken);
}
