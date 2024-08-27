package kr.kro.ddalkak.auth.auth.application.port.out;

import kr.kro.ddalkak.auth.auth.domain.User;

import java.util.Optional;

public interface LoadUserPort {
    /**
     * 사용자 ID를 이용하여 사용자 정보를 조회한다.
     *
     * @param username 사용자 ID
     * @return {@link User} 사용자 정보
     */
    Optional<User> loadUser(String username);
}
