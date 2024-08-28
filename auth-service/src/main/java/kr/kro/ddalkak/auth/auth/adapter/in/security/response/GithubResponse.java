package kr.kro.ddalkak.auth.auth.adapter.in.security.response;


import kr.kro.ddalkak.auth.auth.domain.ProviderType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GithubResponse implements OAuth2Response {
	private final Map<String, Object> attribute;

	public GithubResponse(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

	@Override
	public ProviderType getProvider() {
		return ProviderType.GITHUB;
	}

	@Override
	public String getProviderId() {
		return attribute.get("node_id").toString();
	}

	@Override
	public String getEmail() {
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		return attribute.get("name").toString();
	}
}
