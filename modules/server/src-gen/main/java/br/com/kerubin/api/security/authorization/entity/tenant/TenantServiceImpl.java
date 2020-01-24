/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class TenantServiceImpl implements TenantService {
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private TenantListFilterPredicate tenantListFilterPredicate;
	
	
	@Transactional
	@Override
	public TenantEntity create(TenantEntity tenantEntity) {
		return tenantRepository.save(tenantEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public TenantEntity read(java.util.UUID id) {
		return getTenantEntity(id);
	}
	
	@Transactional
	@Override
	public TenantEntity update(java.util.UUID id, TenantEntity tenantEntity) {
		// TenantEntity entity = getTenantEntity(id);
		// BeanUtils.copyProperties(tenantEntity, entity, "id");
		// entity = tenantRepository.save(entity);
		
		TenantEntity entity = tenantRepository.save(tenantEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		tenantRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		tenantRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<TenantEntity> list(TenantListFilter tenantListFilter, Pageable pageable) {
		Predicate predicate = tenantListFilterPredicate.mountAndGetPredicate(tenantListFilter);
		
		Page<TenantEntity> resultPage = tenantRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected TenantEntity getTenantEntity(java.util.UUID id) {
		Optional<TenantEntity> tenantEntity = tenantRepository.findById(id);
		if (!tenantEntity.isPresent()) {
			throw new IllegalArgumentException("Tenant not found:" + id.toString());
		}
		return tenantEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<TenantAutoComplete> autoComplete(String query) {
		Collection<TenantAutoComplete> result = tenantRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<TenantNameAutoComplete> tenantNameAutoComplete(String query) {
		Collection<TenantNameAutoComplete> result = tenantRepository.tenantNameAutoComplete(query);
		return result;
	}
	
	
}
