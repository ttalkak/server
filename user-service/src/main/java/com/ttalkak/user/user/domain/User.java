package com.ttalkak.user.user.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String accessToken;
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
}
