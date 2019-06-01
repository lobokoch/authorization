package br.com.kerubin.api.security.authorization.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

import br.com.kerubin.api.security.user.SystemUser;

public class CustomTokenEnhancer extends TokenEnhancerChain {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		SystemUser systemUser = (SystemUser) authentication.getPrincipal();
		Map<String, Object> infos = new HashMap<>();
		infos.put("name", systemUser.getUser().getName());
		// Tenant can be null
		String tenant = null;
		if (systemUser.getUser().getTenant() != null) {
			tenant = systemUser.getUser().getTenant().getName();
		}
		infos.put("tenant", tenant);
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infos);
		return accessToken;
		
	}

}
