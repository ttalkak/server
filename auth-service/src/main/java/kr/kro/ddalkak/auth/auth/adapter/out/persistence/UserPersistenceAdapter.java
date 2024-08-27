package kr.kro.ddalkak.auth.auth.adapter.out.persistence;

import kr.kro.ddalkak.auth.common.PersistenceAdapter;
import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntityMapper;
import kr.kro.ddalkak.auth.auth.adapter.out.persistence.repository.UserJpaRepository;
import kr.kro.ddalkak.auth.auth.application.port.out.LoadUserPort;
import kr.kro.ddalkak.auth.auth.application.port.out.SaveUserPort;
import kr.kro.ddalkak.auth.auth.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> loadUser(String username) {
        return userJpaRepository.findByUsername(username)
                .map(UserEntityMapper::toDomain);
    }

    @Override
    public void save(String username, String password, String email, String userRole) {

    }
}
