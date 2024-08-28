package kr.kro.ddalkak.auth.auth.adapter.in.security.response;

import kr.kro.ddalkak.auth.auth.domain.ProviderType;

public interface OAuth2Response {
	ProviderType getProvider();

	String getProviderId();

	String getEmail();

	String getName();

}
