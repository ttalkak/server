package kr.kro.ddalkak.auth.auth.application.service;

import kr.kro.ddalkak.auth.common.UseCase;
import kr.kro.ddalkak.auth.auth.adapter.in.security.JwtTokenProvider;
import kr.kro.ddalkak.auth.auth.application.port.in.SignInUseCase;
import kr.kro.ddalkak.auth.auth.application.port.out.LoadUserPort;
import kr.kro.ddalkak.auth.auth.domain.JwtToken;
import kr.kro.ddalkak.auth.auth.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {
    private final LoadUserPort loadUserPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtToken signIn(String username, String password) {
        User user = loadUserPort.loadUser(username).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getUserRole().toString().toUpperCase())
        );

        return jwtTokenProvider.generate(user.getUsername(), authorities);
    }
}
