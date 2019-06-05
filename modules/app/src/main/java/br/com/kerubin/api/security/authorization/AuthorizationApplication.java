package br.com.kerubin.api.security.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import br.com.kerubin.api.database.core.ServiceContext;

@SpringBootApplication(
		exclude = { 
		        DataSourceAutoConfiguration.class,
		        HibernateJpaAutoConfiguration.class,
		        DataSourceTransactionManagerAutoConfiguration.class
		}
		, scanBasePackages = { "br.com.kerubin.api" }
)
@EnableEurekaClient
public class AuthorizationApplication {

	public static void main(String[] args) {
		init();
		SpringApplication.run(AuthorizationApplication.class, args);
	}
	
	private static void init() {
		ServiceContext.setDefaultDomain(SecurityAuthorizationConstants.DOMAIN);
		ServiceContext.setDefaultService(SecurityAuthorizationConstants.SERVICE);
		// ServiceConnectionProvider.INSTANCE.setMigrateDefaultTenant(true);
	}
}
