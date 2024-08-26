package kr.kro.ddalkak.user.user.adapter.out.persistence.repository;

import kr.kro.ddalkak.user.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
