/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConfigurationProperties("kerubin.web")
@Getter
@Setter
@ToString
public class ServiceConfig {
	
	private static final String ALLOW_ORIGINS = "http://localhost:4200";
	
	private String allowOrigin;
	private boolean enableHttps;
	
	public ServiceConfig() {
		this.allowOrigin = ALLOW_ORIGINS;
	}

}

