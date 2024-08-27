package kr.kro.ddalkak.auth.auth.adapter.in.web.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String userRole;
}
