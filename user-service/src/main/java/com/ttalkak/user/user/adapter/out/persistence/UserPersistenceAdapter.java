package com.ttalkak.user.user.adapter.out.persistence;

import com.ttalkak.user.user.adapter.out.persistence.entity.UserEntity;
import com.ttalkak.user.user.domain.LoginUser;
import com.ttalkak.user.user.domain.UserRole;
import com.ttalkak.user.common.PersistenceAdapter;
import com.ttalkak.user.user.adapter.out.persistence.entity.UserEntityMapper;
import com.ttalkak.user.user.adapter.out.persistence.repository.UserJpaRepository;
import com.ttalkak.user.user.application.port.out.LoadUserPort;
import com.ttalkak.user.user.application.port.out.SaveUserPort;
import com.ttalkak.user.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<LoginUser> loadUser(Long userId) {
        return userJpaRepository.findById(userId)
                .map(UserEntityMapper::toLoginUser);
    }

    @Override
    public Optional<User> loadUser(String username) {
        return userJpaRepository.findByUsername(username)
                .map(UserEntityMapper::toUser);
    }

    @Override
    public void save(String username, String password, String email) {
        UserEntity entity = new UserEntity(username, password, email, UserRole.PROVIDER);
        userJpaRepository.save(entity);
    }

    @Override
    public UserEntity save(String username, String password, String email, String providerId, String accessToken) {
        UserEntity entity = new UserEntity(username, password, email, UserRole.PROVIDER, providerId, accessToken);
        return userJpaRepository.save(entity);
    }
}