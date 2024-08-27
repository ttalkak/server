package kr.kro.ddalkak.auth.auth.adapter.out.persistence.repository;

import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
