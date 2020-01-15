/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

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
public class TenantOpCountServiceImpl implements TenantOpCountService {
	
	@Autowired
	private TenantOpCountRepository tenantOpCountRepository;
	
	@Autowired
	private TenantOpCountListFilterPredicate tenantOpCountListFilterPredicate;
	
	
	@Autowired
	private TenantRepository tenantRepository;
	
	
	@Transactional
	@Override
	public TenantOpCountEntity create(TenantOpCountEntity tenantOpCountEntity) {
		return tenantOpCountRepository.save(tenantOpCountEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public TenantOpCountEntity read(java.util.UUID id) {
		return getTenantOpCountEntity(id);
	}
	
	@Transactional
	@Override
	public TenantOpCountEntity update(java.util.UUID id, TenantOpCountEntity tenantOpCountEntity) {
		// TenantOpCountEntity entity = getTenantOpCountEntity(id);
		// BeanUtils.copyProperties(tenantOpCountEntity, entity, "id");
		// entity = tenantOpCountRepository.save(entity);
		
		TenantOpCountEntity entity = tenantOpCountRepository.save(tenantOpCountEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		tenantOpCountRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		tenantOpCountRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<TenantOpCountEntity> list(TenantOpCountListFilter tenantOpCountListFilter, Pageable pageable) {
		Predicate predicate = tenantOpCountListFilterPredicate.mountAndGetPredicate(tenantOpCountListFilter);
		
		Page<TenantOpCountEntity> resultPage = tenantOpCountRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected TenantOpCountEntity getTenantOpCountEntity(java.util.UUID id) {
		Optional<TenantOpCountEntity> tenantOpCountEntity = tenantOpCountRepository.findById(id);
		if (!tenantOpCountEntity.isPresent()) {
			throw new IllegalArgumentException("TenantOpCount not found:" + id.toString());
		}
		return tenantOpCountEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<TenantOpCountAutoComplete> autoComplete(String query) {
		Collection<TenantOpCountAutoComplete> result = tenantOpCountRepository.autoComplete(query);
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
	
	
	
}
