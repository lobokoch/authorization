package br.com.kerubin.api.security.component;

import static br.com.kerubin.api.servicecore.util.CoreUtils.getValue;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.security.authorization.entity.sysuser.QSysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;
import br.com.kerubin.api.servicecore.error.ForbiddenOperationException;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserHelperImpl implements UserHelper {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	@Inject
	private TenantRepository tenantRepository;
	
	@Transactional(readOnly = true)
	@Override
	public long getNumberOfUsersForTenant(TenantEntity tenant) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QSysUserEntity qUser = QSysUserEntity.sysUserEntity;
		
		JPAQuery<Long> sql = query
			.select(Expressions.ONE.count())
			.from(qUser)
			.where(qUser.tenant.id.eq(tenant.getId()));
		
		Long result = getValue(sql.fetchOne(), 0L);
		return result;
	}
	
	@Override
	public long getMaxConfiguredUsersForTenant(SysUserEntity user) {
		TenantEntity tenant = user.getTenant();
		String userEmail = user.getEmail();
		if (isEmpty(tenant)) {
			log.error("Usuário {} sem tenant.", userEmail);
			throw new UserAccountException("Usuário sem tenant.");
		}
		
		long maxUsers = getValue(tenant.getMaxUsers(), 1L);
		return maxUsers;
	}
	
	@Transactional(readOnly = true)
	@Override
	public void checkMaxUsersForTenantOnUserCreation(SysUserEntity user) {
		checkMaxUsersForTenant(user, true);
	}
	
	@Transactional(readOnly = true)
	@Override
	public void checkMaxUsersForTenantOnUserUpdate(SysUserEntity user) {
		checkMaxUsersForTenant(user, false);
	}
	
	@Override
	public void checkOnlyAdministratorCanDo(SysUserEntity user) {
		if (!user.getAdministrator()) {
			throw new ForbiddenOperationException("Operação não permitida para este usuário.");
		}
	}
	
	@Override
	public void checkOnlySuperAdministratorCanDo(SysUserEntity user) {
		// Sum power with super power
		if (!user.getAdministrator() || !user.getSuperAdministrator()) {
			throw new ForbiddenOperationException("Operação não permitida para este usuário (2).");
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public SysUserEntity getContextUser() {
		SysUserEntity user = sysUserRepository.findByEmailIgnoreCase(ServiceContext.getUser()).orElse(null);
		return user;
	}
	
	@Transactional(readOnly = true)
	@Override
	public TenantEntity getContextTenant() {
		TenantEntity tenant = tenantRepository.findByNameIgnoreCase(ServiceContext.getTenant()).orElse(null);
		return tenant;
	}
	
	private void checkMaxUsersForTenant(SysUserEntity user, boolean onCreation) {
		long maxConfiguredUsersForTenant = getMaxConfiguredUsersForTenant(user);
		long tenantCountUsers = getNumberOfUsersForTenant(user.getTenant());
		int augment = onCreation ? 1 : 0;
		if (tenantCountUsers + augment > maxConfiguredUsersForTenant) {
			throw new UserAccountException("Número máximo de usuários excedido. Contate o serviço de suporte para poder criar mais usuários.");
		}
	}

}
