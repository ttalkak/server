package kr.kro.ddalkak.user.user.application.port.out;

import kr.kro.ddalkak.user.user.adapter.out.persistence.entity.UserEntity;
import kr.kro.ddalkak.user.user.domain.User;

public interface LoadUserPort {
    /**
     * 사용자 ID를 이용하여 사용자 정보를 조회한다.
     *
     * @param username 사용자 ID
     * @return {@link User} 사용자 정보
     */
    User loadUser(String username);
}
