package kr.kro.ddalkak.user.user.adapter.in.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kro.ddalkak.user.common.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

import static kr.kro.ddalkak.user.user.adapter.in.security.CustomAuthorizationRepository.REDIRECT_URI_PARAM_COOKIE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final CustomAuthorizationRepository authorizationRepository;

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE).map(Cookie::getValue);
        clearAuthenticationAttributes(request, response);
        return redirectUri.orElse(getDefaultTargetUrl());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
//        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

//        JwtToken jwtToken = jwtService.generateToken(customUserDetails.getUsername(), customUserDetails.getAuthorities());
//        CookieUtils.addCookie(response, AuthConst.REFRESH_TOKEN, jwtToken.getRefreshToken(), properties.getRefreshExpire(), true);

        String redirectURI = determineTargetUrl(request, response, authentication);
        getRedirectStrategy().sendRedirect(request, response, getRedirectUrl(redirectURI, ""));
    }

    private String getRedirectUrl(String redirectURI, String accessToken) {
        return UriComponentsBuilder.fromUriString(redirectURI + "/auth/callback")
                .queryParam("accessToken", accessToken)
                .build()
                .toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        authorizationRepository.removeAuthorizationRequest(request, response);
    }
}
