package kr.kro.ddalkak.auth.auth.adapter.in.web.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
