package br.com.kerubin.api.security.authorization.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.com.kerubin.api.security.authorization.token.CustomTokenEnhancer;

//@Profile
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final int MAX_ACCESS_TOKEN_VALIDITY_SECONDS = 60; // seconds, 1 minute
	private static final int MAX_REFRESH_TOKEN_VALIDITY_SECONDS = 3600 * 24; // 1 day
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// kerubin-fe:$2a$10$rjUiHb5.zhDsffabnl68e.JDNUWrJgNe8hysgbQuZAeZ4nwV3F/UK
	// Angel!81:$2a$10$/5hsSmGRpncCZW8ImsoxAu86npTGLsZU5ur1ZZJMEC1zFGGGOvZXO

	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("kerubin-fe") // kerubin-fe
		//.secret("$2a$10$/5hsSmGRpncCZW8ImsoxAu86npTGLsZU5ur1ZZJMEC1zFGGGOvZXO") // Angel!81
		.secret("$2a$10$xO99TFPoDDSDGPUg8/dEjOU2fSI/UX9bUVjFi.DA7ZrHhRcl5nPqK") // 123
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		//.accessTokenValiditySeconds(1800) // 30 min
		.accessTokenValiditySeconds(MAX_ACCESS_TOKEN_VALIDITY_SECONDS) 
		.refreshTokenValiditySeconds(MAX_REFRESH_TOKEN_VALIDITY_SECONDS) // 1dia
	.and()
		.withClient("mobile")
		.secret("$2a$10$KJRZ.d9lgifvJU420wX7Oeb2sA3mgnGjv9iyUWNqcN1RxjXnKfcKK") // m0b1l30
		.scopes("read")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(1800) // 30 minutos
		.refreshTokenValiditySeconds(3600 * 24); // 1 dia
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancerChain)
			.reuseRefreshTokens(false)
			.userDetailsService(userDetailsService)
			.authenticationManager(authenticationManager);
		
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("123"); //kerubin
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
	    return new CustomTokenEnhancer();
	}

}
