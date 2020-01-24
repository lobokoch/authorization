/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;


import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserBaseRepository sysUserBaseRepository;
	
	@Autowired
	private SysUserListFilterPredicate sysUserListFilterPredicate;
	
	
	@Autowired
	private TenantRepository tenantRepository;
	
	
	@Transactional
	@Override
	public SysUserEntity create(SysUserEntity sysUserEntity) {
		return sysUserBaseRepository.save(sysUserEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public SysUserEntity read(java.util.UUID id) {
		return getSysUserEntity(id);
	}
	
	@Transactional
	@Override
	public SysUserEntity update(java.util.UUID id, SysUserEntity sysUserEntity) {
		// SysUserEntity entity = getSysUserEntity(id);
		// BeanUtils.copyProperties(sysUserEntity, entity, "id");
		// entity = sysUserBaseRepository.save(entity);
		
		SysUserEntity entity = sysUserBaseRepository.save(sysUserEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		sysUserBaseRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		sysUserBaseRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<SysUserEntity> list(SysUserListFilter sysUserListFilter, Pageable pageable) {
		Predicate predicate = sysUserListFilterPredicate.mountAndGetPredicate(sysUserListFilter);
		
		Page<SysUserEntity> resultPage = sysUserBaseRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected SysUserEntity getSysUserEntity(java.util.UUID id) {
		Optional<SysUserEntity> sysUserEntity = sysUserBaseRepository.findById(id);
		if (!sysUserEntity.isPresent()) {
			throw new IllegalArgumentException("SysUser not found:" + id.toString());
		}
		return sysUserEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<SysUserAutoComplete> autoComplete(String query) {
		Collection<SysUserAutoComplete> result = sysUserBaseRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(String query) {
		Collection<TenantAutoComplete> result = tenantRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<SysUserNameAutoComplete> sysUserNameAutoComplete(String query) {
		Collection<SysUserNameAutoComplete> result = sysUserBaseRepository.sysUserNameAutoComplete(query);
		return result;
	}
	
	
}
