package kr.kro.ddalkak.auth.auth.application.port.out;

import kr.kro.ddalkak.auth.auth.domain.UserRole;

public interface SaveUserPort {
    /**
     * 사용자 정보를 저장한다.
     *
     * @param username 사용자 ID
     * @param password 비밀번호
     * @param email 이메일
     * @param role 사용자 권한
     */
    void save(String username, String password, String email, UserRole role);
}
