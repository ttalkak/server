package kr.kro.ddalkak.auth.auth.application.service;

import kr.kro.ddalkak.auth.common.UseCase;
import kr.kro.ddalkak.auth.auth.adapter.in.security.JwtTokenProvider;
import kr.kro.ddalkak.auth.auth.application.port.in.RefreshTokenUseCase;
import kr.kro.ddalkak.auth.auth.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtToken refresh(String refreshToken) {
        return jwtTokenProvider.refreshToken(refreshToken);
    }
}
