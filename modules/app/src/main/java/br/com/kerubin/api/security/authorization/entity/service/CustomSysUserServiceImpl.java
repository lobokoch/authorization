package br.com.kerubin.api.security.authorization.entity.service;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserListFilter;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserNameAutoComplete;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserServiceImpl;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;
import br.com.kerubin.api.servicecore.error.ForbiddenOperationException;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;

@Primary
@Service
public class CustomSysUserServiceImpl extends SysUserServiceImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public SysUserEntity create(SysUserEntity sysUserEntity) {
		SysUserEntity user = getContextUser();
		onlyAdministratorCanDo(user);
		
		validateSenha(sysUserEntity);
		sysUserEntity.setAccountType(user.getAccountType());
		sysUserEntity.setTenant(user.getTenant());
		
		initSession();
		try {
			sysUserEntity.setPassword(passwordEncoder.encode(sysUserEntity.getPassword()));
			return super.create(sysUserEntity);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional
	@Override
	public SysUserEntity update(UUID id, SysUserEntity sysUserEntity) {
		initSession();
		
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			if (StringUtils.containsOnly(sysUserEntity.getPassword(), '*')) { // Não alterou, mantém a antiga já codificada.
				SysUserEntity theUser = getSysUserEntity(id);
				sysUserEntity.setPassword(theUser.getPassword());
			}
			else { // Alterou, então valida e codifica
				validateSenha(sysUserEntity);
				sysUserEntity.setPassword(passwordEncoder.encode(sysUserEntity.getPassword()));
			}
			
			if (user.getId().equals(id)) { // Algumas validações relacionadas a coisas que o usuário corrente não deve fazer com ele mesmo
				if (!sysUserEntity.getActive()) {
					throw new UserAccountException("Não pode desativar o usuário corrente.");
				}
			}
			
			return super.update(id, sysUserEntity);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional
	@Override
	public void delete(UUID id) {
		initSession();
		try {
			
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			if (user.getId().equals(id)) { // Algumas validações relacionadas a coisas que o usuário corrente não deve fazer com ele mesmo
				throw new UserAccountException("Não pode excluir o usuário corrente.");
			}
			
			SysUserEntity theUser = getSysUserEntity(id);
			if (theUser.getAdministrator()) {
				throw new UserAccountException("Não pode excluir um usuário administrador.");
			}
			
			super.delete(id);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<SysUserEntity> list(SysUserListFilter sysUserListFilter, Pageable pageable) {
		initSession();
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			Page<SysUserEntity> result = super.list(sysUserListFilter, pageable);
			return result;
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public SysUserEntity read(UUID id) {
		initSession();
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			return super.read(id);
		}
		finally {
			endSession();
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	protected SysUserEntity getSysUserEntity(UUID id) {
		initSession();
		try {
			return super.getSysUserEntity(id);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<SysUserAutoComplete> autoComplete(String query) {
		initSession();
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			return super.autoComplete(query);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(String query) {
		initSession();
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			return super.tenantTenantAutoComplete(query);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<SysUserNameAutoComplete> sysUserNameAutoComplete(String query) {
		initSession();
		try {
			SysUserEntity user = getContextUser();
			onlyAdministratorCanDo(user);
			
			return super.sysUserNameAutoComplete(query);
		}
		finally {
			endSession();
		}
	}
	
	private void onlyAdministratorCanDo(SysUserEntity user) {
		if (!user.getAdministrator()) {
			throw new ForbiddenOperationException("Operação não permitida para este usuário.");
		}
	}
	
	private void validateSenha(SysUserEntity sysUserEntity) {
		if (isEmpty(sysUserEntity.getPassword())) {
			throw new UserAccountException("A senha deve ser informada.");
		}
		
		if (StringUtils.containsOnly(sysUserEntity.getPassword(), '*')) {
			throw new UserAccountException("Senha inválida.");
		}
		
		if (sysUserEntity.getPassword().length() < 5) {
			throw new UserAccountException("A senha deve ter no mínimo 5 caracteres.");
		}
		
		if (!sysUserEntity.getPassword().equals(sysUserEntity.getConfirmPassword())) {
			throw new UserAccountException("A senha e a confirmação da senha devem ser iguais.");
		}
	}
	
	private void initSession() {
		SysUserEntity user = getContextUser();
		
		UUID tenatId = user.getTenant().getId();
		
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("userFilter");
		filter.setParameter("tenant", tenatId);
	}
	
	private SysUserEntity getContextUser() {
		SysUserEntity user = sysUserRepository.findByEmailIgnoreCase(ServiceContext.getUser()).orElse(null);
		if (user == null) {
			throw new UserAccountException("Contexto sem usuário.");
		}
		return user;
	}
	
	private void endSession() {
		Session session = em.unwrap(Session.class);
		session.disableFilter("userFilter");
	}
	
	private boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		
		if (value instanceof String) {
			return value.toString().trim().isEmpty();
		}
		
		return false;
	}

}
