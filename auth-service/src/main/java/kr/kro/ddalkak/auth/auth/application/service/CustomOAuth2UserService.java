package kr.kro.ddalkak.auth.auth.application.service;


import kr.kro.ddalkak.auth.auth.adapter.in.security.response.CustomOAuthUserFactory;
import kr.kro.ddalkak.auth.auth.adapter.in.security.response.OAuth2Response;
import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntity;
import kr.kro.ddalkak.auth.auth.adapter.out.persistence.entity.UserEntityMapper;
import kr.kro.ddalkak.auth.auth.application.port.out.LoadUserPort;
import kr.kro.ddalkak.auth.auth.application.port.out.SaveUserPort;
import kr.kro.ddalkak.auth.auth.domain.CustomOAuth2User;
import kr.kro.ddalkak.auth.auth.domain.ProviderType;
import kr.kro.ddalkak.auth.auth.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final LoadUserPort loadUserPort;
	private final SaveUserPort saveUserPort;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		ProviderType providerType = ProviderType.valueOf(registrationId.toUpperCase());
		OAuth2Response response = CustomOAuthUserFactory.parseOAuth2Response(providerType, oauth2User.getAttributes());


		String username = response.getEmail();

		User user = loadUserPort.loadUser(username).orElseGet(() -> {
			String encodedPassword = passwordEncoder.encode(response.getEmail());
			UserEntity entity = saveUserPort.save(username, encodedPassword, response.getEmail(), response.getProviderId());
			return UserEntityMapper.toDomain(entity);
		});

		return new CustomOAuth2User(user);
	}
}
