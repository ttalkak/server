package kr.kro.ddalkak.auth.auth.adapter.in.security.response;

import kr.kro.ddalkak.auth.auth.domain.ProviderType;

import java.util.Map;

public class CustomOAuthUserFactory {
    public static OAuth2Response parseOAuth2Response(ProviderType providerType, Map<String, Object> attributes) {
        return switch (providerType) {
            case GITHUB -> new GithubResponse(attributes);
        };
    }
}
