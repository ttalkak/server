package kr.kro.ddalkak.auth.auth.application.service;

import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntity;
import kr.kro.ddalkak.auth.auth.domain.UserRole;
import kr.kro.ddalkak.auth.common.UseCase;
import kr.kro.ddalkak.auth.auth.application.port.in.RegisterCommand;
import kr.kro.ddalkak.auth.auth.application.port.in.SignUpUseCase;
import kr.kro.ddalkak.auth.auth.application.port.out.LoadUserPort;
import kr.kro.ddalkak.auth.auth.application.port.out.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {
    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;

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
