package kr.kro.ddalkak.user.user.domain;

import lombok.Data;

@Data
public class JwtToken {
    private String accessToken;
    private String refreshToken;
}
