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
	
	private static final String TENANT_PARAM = "tenant";
	//private static final String USERNAME_PARAM = "username";
	private static final String USER_SUPER_ADMIN_PARAM = "superAdmin";
	private static final String NAME_PARAM = "name";
	private static final String TENANT_ACCOUNT_TYPE_PARAM = "tenantAccountType";
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {		
		SystemUser systemUser = (SystemUser) authentication.getPrincipal();
		SysUserEntity user = systemUser.getUser();
		
		Map<String, Object> infos = new HashMap<>();
		
		infos.put(NAME_PARAM, user.getName());
		
		// Aqui parece que já está adicionando, porém ainda não encontrei o lugar. Vi no front: this.jwtPayload.user_name
		// infos.put(USERNAME_PARAM, user.getEmail());
		
		// Tenant can be null
		String tenant = null;
		if (user.getTenant() != null) {
			tenant = user.getTenant().getName();
		}
		infos.put(TENANT_PARAM, tenant);
		
		infos.put(TENANT_ACCOUNT_TYPE_PARAM, user.getAccountType().name().toUpperCase());
		
		if (user.getActive() && user.getAdministrator() && user.getSuperAdministrator()) {
			infos.put(USER_SUPER_ADMIN_PARAM, true);
		}
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infos);
		return accessToken;
		
	}

}
