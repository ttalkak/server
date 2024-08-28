package kr.kro.ddalkak.auth.auth.application.port.out;

import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntity;
import kr.kro.ddalkak.auth.auth.domain.UserRole;

public interface SaveUserPort {
    /**
     * 사용자 정보를 저장한다. (
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     */
    void save(String username, String password, String email);

    /**
     * 사용자 정보를 저장한다. (OAuth2 사용자 전용 Provider)
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     * @param providerId OAuth2 제공자 ID
     * @return 저장된 사용자 정보
     */
    UserEntity save(String username, String password, String email, String providerId);
}
