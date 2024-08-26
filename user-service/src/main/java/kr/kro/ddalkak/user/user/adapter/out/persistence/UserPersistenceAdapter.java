package kr.kro.ddalkak.user.user.adapter.out.persistence;

import kr.kro.ddalkak.user.common.PersistenceAdapter;
import kr.kro.ddalkak.user.user.adapter.out.persistence.repository.UserJpaRepository;
import kr.kro.ddalkak.user.user.application.port.out.LoadUserPort;
import kr.kro.ddalkak.user.user.domain.User;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class    UserPersistenceAdapter implements LoadUserPort {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User loadUser(String username) {
        return null;
    }
}
