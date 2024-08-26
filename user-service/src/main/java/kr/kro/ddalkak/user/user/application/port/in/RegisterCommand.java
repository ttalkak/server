package kr.kro.ddalkak.user.user.application.port.in;

import kr.kro.ddalkak.user.user.domain.UserRole;
import lombok.Data;

@Data
public class RegisterCommand {
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
}
