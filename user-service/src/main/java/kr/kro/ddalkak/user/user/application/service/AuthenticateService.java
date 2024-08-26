package kr.kro.ddalkak.user.user.application.service;

import kr.kro.ddalkak.user.common.UseCase;
import kr.kro.ddalkak.user.user.application.port.in.AuthenticateUserUseCase;
import kr.kro.ddalkak.user.user.application.port.out.LoadUserPort;
import kr.kro.ddalkak.user.user.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthenticateService implements AuthenticateUserUseCase {
    private final LoadUserPort loadUserPort;

    @Override
    public JwtToken authenticate(String username, String password) {
        loadUserPort.loadUser(username);
        return null;
    }
}
