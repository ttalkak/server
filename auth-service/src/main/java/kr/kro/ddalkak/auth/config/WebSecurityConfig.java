package kr.kro.ddalkak.auth.config;

import kr.kro.ddalkak.auth.auth.adapter.in.security.CustomAuthorizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final CustomAuthorizationRepository authorizationRepository;
    private final String[] ALLOWED_URLS = {
            "/actuator"
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity security) throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth.requestMatchers(ALLOWED_URLS).permitAll()
                        .anyRequest().authenticated()
                ).oauth2Login((oauth2) -> oauth2.authorizationEndpoint(
                        authorization ->
                            authorization.baseUri("/oauth2/authorization")
                                    .authorizationRequestRepository(authorizationRepository)
                        ).redirectionEndpoint(redirect -> redirect.baseUri("/*/oauth2/code/*"))
                )
                .build();
    }
}
