package br.com.kerubin.api.security.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import br.com.kerubin.api.database.core.ServiceConnectionProvider;
import br.com.kerubin.api.database.core.ServiceContext;

@EnableEurekaClient
@SpringBootApplication(
		exclude = { 
		        DataSourceAutoConfiguration.class,
		        HibernateJpaAutoConfiguration.class,
		        DataSourceTransactionManagerAutoConfiguration.class
		}
		, scanBasePackages = { "br.com.kerubin.api" }
)
public class SecurityAuthorizationApplication {
	
	private static final String SECURITY_AUTHORIZATION_DEFAULT_TENANT = ServiceContext.DEFAULT_USER + "_" + 
			SecurityAuthorizationConstants.DOMAIN + "_" + SecurityAuthorizationConstants.SERVICE;

	public static void main(String[] args) {
		init();
		SpringApplication.run(SecurityAuthorizationApplication.class, args);
	}
	
	private static void init() {
		ServiceContext.setDefaultDomain(SecurityAuthorizationConstants.DOMAIN);
		ServiceContext.setDefaultService(SecurityAuthorizationConstants.SERVICE);
		ServiceContext.setDefaultTenantProvider(SecurityAuthorizationApplication::securityAuthorizationDefaultTenantProvider);
		ServiceConnectionProvider.INSTANCE.setMigrateDefaultTenant(true);
	}
	
	public static String securityAuthorizationDefaultTenantProvider(String currentDefaultTenant) {
		return ServiceContext.DEFAULT_USER;
	}
}
