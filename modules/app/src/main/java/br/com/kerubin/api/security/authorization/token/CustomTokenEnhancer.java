package br.com.kerubin.api.security.authorization.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.user.SystemUser;

public class CustomTokenEnhancer extends TokenEnhancerChain {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {		
		SystemUser systemUser = (SystemUser) authentication.getPrincipal();
		SysUserEntity user = systemUser.getUser();
		
		Map<String, Object> infos = new HashMap<>();
		infos.put("name", user.getName());
		// Tenant can be null
		String tenant = null;
		if (user.getTenant() != null) {
			tenant = user.getTenant().getName();
		}
		infos.put("tenant", tenant);
		
		if (user.getActive() && user.getAdministrator() && user.getSuperAdministrator()) {
			infos.put("superAdmin", true);
		}
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infos);
		return accessToken;
		
	}

}
