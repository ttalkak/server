package kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity;

import kr.kro.ddalkak.auth.auth.domain.User;

public class UserEntityMapper {
    public static UserEntity toEntity(User user) {
        return null;
    }

    public static User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setUserRole(userEntity.getUserRole());
        return user;
    }
}
