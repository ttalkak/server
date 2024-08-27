package kr.kro.ddalkak.auth.auth.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
}
