package br.com.kerubin.api.security.authorization.entity.service;

import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserListFilter;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserServiceImpl;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;

@Primary
@Service
public class CustomSysUserServiceImpl extends SysUserServiceImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	@Override
	public Page<SysUserEntity> list(SysUserListFilter sysUserListFilter, Pageable pageable) {
		SysUserEntity user = sysUserRepository.findByEmailIgnoreCase(ServiceContext.getUser()).orElse(null);
		if (user == null) {
			throw new UserAccountException("Contexto sem usuário.");
		}
		
		initSession(user.getTenant().getId());
		try {
			Page<SysUserEntity> result = super.list(sysUserListFilter, pageable);
			return result;
		}
		finally {
			endSession();
		}
	}
	
	private void initSession(UUID tenant) {
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("userFilter");
		filter.setParameter("tenant", tenant);
	}
	
	private void endSession() {
		Session session = em.unwrap(Session.class);
		session.disableFilter("userFilter");
	}

}
