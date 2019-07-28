package br.com.kerubin.api.security.authorization.entity.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUser;

public class SecuriryServiceImpl implements SecuriryService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public List<SysUser> listActiveUsers() {
		return null;
	}

}
