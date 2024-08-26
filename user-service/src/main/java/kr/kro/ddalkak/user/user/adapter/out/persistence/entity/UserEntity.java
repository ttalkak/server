package kr.kro.ddalkak.user.user.adapter.out.persistence.entity;

import jakarta.persistence.*;
import kr.kro.ddalkak.user.user.domain.UserRole;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique_constraint", columnNames = "username")
        }
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 300)
    private String password;

    private String email;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "is_email_verified", nullable = false)
    @ColumnDefault("false")
    private boolean isEmailVerified = false;
}
