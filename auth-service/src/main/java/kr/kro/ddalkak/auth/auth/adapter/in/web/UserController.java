package kr.kro.ddalkak.auth.auth.adapter.in.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kro.ddalkak.auth.common.util.CookieUtils;
import kr.kro.ddalkak.auth.auth.adapter.in.web.request.SignInRequest;
import kr.kro.ddalkak.auth.auth.adapter.in.web.request.SignUpRequest;
import kr.kro.ddalkak.auth.auth.adapter.in.web.response.ApiResponse;
import kr.kro.ddalkak.auth.auth.application.port.in.RefreshTokenUseCase;
import kr.kro.ddalkak.auth.auth.application.port.in.RegisterCommand;
import kr.kro.ddalkak.auth.auth.application.port.in.SignInUseCase;
import kr.kro.ddalkak.auth.auth.application.port.in.SignUpUseCase;
import kr.kro.ddalkak.auth.auth.domain.JwtToken;
import kr.kro.ddalkak.auth.auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static kr.kro.ddalkak.auth.auth.adapter.in.security.JwtTokenProvider.REFRESH_TOKEN_COOKIE;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final SignUpUseCase signUpUseCase;
    private final SignInUseCase signInUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @Value("${jwt.refresh-expire}")
    private int refreshExpire;

    @PostMapping("/sign-up")
    public ApiResponse<Void> signUp(@RequestBody SignUpRequest request) {
        RegisterCommand command = new RegisterCommand(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                UserRole.valueOf(request.getUserRole())
        );

        signUpUseCase.signUp(command);
        return ApiResponse.success();
    }

    @PostMapping("/sign-in")
    public ApiResponse<JwtToken> loadUser(@RequestBody SignInRequest request) {
        return ApiResponse.success(
                signInUseCase.signIn(request.getUsername(), request.getPassword())
        );
    }

    @PostMapping("/refresh")
    public ApiResponse<JwtToken> refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Cookie cookie = CookieUtils.getCookie(request, REFRESH_TOKEN_COOKIE).orElseThrow(
                () -> new IllegalStateException("리프레시 토큰이 없습니다.")
        );

        JwtToken refresh = refreshTokenUseCase.refresh(cookie.getValue());

        CookieUtils.removeCookie(response, REFRESH_TOKEN_COOKIE);
        CookieUtils.addCookie(response, REFRESH_TOKEN_COOKIE, refresh.getRefreshToken(), refreshExpire, true);

        return ApiResponse.success(refresh);
    }
}
