package kr.kro.ddalkak.auth.auth.adapter.in.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kro.ddalkak.auth.common.util.CookieUtils;
import kr.kro.ddalkak.auth.auth.domain.CustomOAuth2User;
import kr.kro.ddalkak.auth.auth.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

import static kr.kro.ddalkak.auth.auth.adapter.in.security.CustomAuthorizationRepository.REDIRECT_URI_PARAM_COOKIE;
import static kr.kro.ddalkak.auth.auth.adapter.in.security.JwtTokenProvider.REFRESH_TOKEN_COOKIE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final CustomAuthorizationRepository authorizationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.refresh-expire}")
    private int refreshExpire;

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE).map(Cookie::getValue);
        clearAuthenticationAttributes(request, response);
        return redirectUri.orElse(getDefaultTargetUrl());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        JwtToken jwtToken = jwtTokenProvider.generate(customUserDetails.getUsername(), customUserDetails.getAuthorities());
        CookieUtils.addCookie(response, REFRESH_TOKEN_COOKIE, jwtToken.getRefreshToken(), refreshExpire, true);

        String redirectURI = determineTargetUrl(request, response, authentication);
        getRedirectStrategy().sendRedirect(request, response, getRedirectUrl(redirectURI, jwtToken.getAccessToken()));
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
