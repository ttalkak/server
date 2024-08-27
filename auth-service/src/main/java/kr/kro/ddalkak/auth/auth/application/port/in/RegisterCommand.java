package kr.kro.ddalkak.auth.auth.application.port.in;

import kr.kro.ddalkak.auth.auth.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterCommand {
    private String username;
    private String password;
    private String email;
}
