package br.com.kerubin.api.security.user;

import static br.com.kerubin.api.servicecore.util.Configs.getAnonymousUser;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafeValue;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isLt;
import static br.com.kerubin.api.tenant.billing.model.BillingConstants.OPERATION_COST;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.kerubin.api.security.authorization.entity.sysuser.QSysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserBaseRepository;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.user.account.exception.UserAccountException;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	private static final String CREDENCIAIS_INVALIDAS = "Credênciais inválidas.";

	private static final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);
	
	@Autowired
	private SysUserBaseRepository sysUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		boolean isAnonymous = isAnonymousUser(username);
		
		QSysUserEntity qUser = QSysUserEntity.sysUserEntity;
		Predicate predicate = qUser.email.eq(username);
		
		Optional<SysUserEntity> userOptional = sysUserRepository.findOne(predicate);
		SysUserEntity user = userOptional.orElseThrow(() -> new UsernameNotFoundException(CREDENCIAIS_INVALIDAS));
		
		if (!user.getActive()) {
			log.warn("User {} is not active yet.", user.getEmail());
			throw new UsernameNotFoundException(CREDENCIAIS_INVALIDAS);
		}
		
		TenantEntity tenant = user.getTenant();
		if (isEmpty(tenant)) {
			log.warn("User {} with null tenant.", user.getEmail());
			throw new UserAccountException("Identificação incorreta.");
		}
		
		String tenantName = tenant.getName();
		
		if (!isAnonymous) {
			BigDecimal saldoDoTenant = getSafeValue(tenant.getBalance());
			if (isLt(saldoDoTenant, OPERATION_COST)) {
				log.warn("Tenant: {},  user: {} does not has enough credit for operations.", tenantName, user.getEmail());
				throw new UserAccountException("Limite esgotado. Por favor contate o suporte.");
			}
		}
		
		return new SystemUser(user, getUserPermissions(user));
	}
	
	private boolean isAnonymousUser(String username) {
		return getAnonymousUser().equals(username);
	}

	private Collection<? extends GrantedAuthority> getUserPermissions(SysUserEntity user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		authorities.add(new SimpleGrantedAuthority("foo"));
		authorities.add(new SimpleGrantedAuthority("baa"));
		
		return authorities;
	}
	

}
