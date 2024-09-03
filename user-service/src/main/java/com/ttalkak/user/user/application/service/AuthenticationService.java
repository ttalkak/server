package com.ttalkak.user.user.application.service;

import com.ttalkak.user.common.UseCase;
import com.ttalkak.user.user.adapter.in.security.JwtTokenProvider;
import com.ttalkak.user.user.application.port.in.AuthenticationUseCase;
import com.ttalkak.user.user.application.port.in.RegisterCommand;
import com.ttalkak.user.user.application.port.out.LoadUserPort;
import com.ttalkak.user.user.application.port.out.SaveUserPort;
import com.ttalkak.user.user.domain.JwtToken;
import com.ttalkak.user.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
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

        return jwtTokenProvider.generate(user.getId(), authorities);
    }

    @Override
    public void signUp(RegisterCommand command) {
        // 사용자 ID 중복 체크
        loadUserPort.loadUser(command.getUsername()).ifPresent(user -> {
            throw new IllegalStateException("이미 사용중인 사용자 ID 입니다.");
        });

        String encodedPassword = passwordEncoder.encode(command.getPassword());

        saveUserPort.save(command.getUsername(), encodedPassword, command.getEmail());
    }
}