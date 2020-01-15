/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserBaseRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.math.BigDecimal;
 
@Service
public class CreditOrderAdminServiceImpl implements CreditOrderAdminService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CreditOrderAdminRepository creditOrderAdminRepository;
	
	@Autowired
	private CreditOrderAdminListFilterPredicate creditOrderAdminListFilterPredicate;
	
	
	@Autowired
	private SysUserBaseRepository sysUserBaseRepository;
	
	
	@Transactional
	@Override
	public CreditOrderAdminEntity create(CreditOrderAdminEntity creditOrderAdminEntity) {
		return creditOrderAdminRepository.save(creditOrderAdminEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderAdminEntity read(java.util.UUID id) {
		return getCreditOrderAdminEntity(id);
	}
	
	@Transactional
	@Override
	public CreditOrderAdminEntity update(java.util.UUID id, CreditOrderAdminEntity creditOrderAdminEntity) {
		// CreditOrderAdminEntity entity = getCreditOrderAdminEntity(id);
		// BeanUtils.copyProperties(creditOrderAdminEntity, entity, "id");
		// entity = creditOrderAdminRepository.save(entity);
		
		CreditOrderAdminEntity entity = creditOrderAdminRepository.save(creditOrderAdminEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		creditOrderAdminRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		creditOrderAdminRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CreditOrderAdminEntity> list(CreditOrderAdminListFilter creditOrderAdminListFilter, Pageable pageable) {
		Predicate predicate = creditOrderAdminListFilterPredicate.mountAndGetPredicate(creditOrderAdminListFilter);
		
		Page<CreditOrderAdminEntity> resultPage = creditOrderAdminRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CreditOrderAdminEntity getCreditOrderAdminEntity(java.util.UUID id) {
		Optional<CreditOrderAdminEntity> creditOrderAdminEntity = creditOrderAdminRepository.findById(id);
		if (!creditOrderAdminEntity.isPresent()) {
			throw new IllegalArgumentException("CreditOrderAdmin not found:" + id.toString());
		}
		return creditOrderAdminEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CreditOrderAdminAutoComplete> autoComplete(String query) {
		Collection<CreditOrderAdminAutoComplete> result = creditOrderAdminRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<SysUserAutoComplete> sysUserOrderUserAutoComplete(String query) {
		Collection<SysUserAutoComplete> result = sysUserBaseRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CreditOrderAdminOrderUserNameAutoComplete> creditOrderAdminOrderUserNameAutoComplete(String query) {
		Collection<CreditOrderAdminOrderUserNameAutoComplete> result = creditOrderAdminRepository.creditOrderAdminOrderUserNameAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CreditOrderAdminOrderTenantNameAutoComplete> creditOrderAdminOrderTenantNameAutoComplete(String query) {
		Collection<CreditOrderAdminOrderTenantNameAutoComplete> result = creditOrderAdminRepository.creditOrderAdminOrderTenantNameAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderAdminSumFields getCreditOrderAdminSumFields(CreditOrderAdminListFilter creditOrderAdminListFilter) {
		Predicate predicate = creditOrderAdminListFilterPredicate.mountAndGetPredicate(creditOrderAdminListFilter);
		
		QCreditOrderAdminEntity qEntity = QCreditOrderAdminEntity.creditOrderAdminEntity;
		JPAQueryFactory query = new JPAQueryFactory(em);
		CreditOrderAdminSumFields result = query.select(
				Projections.bean(CreditOrderAdminSumFields.class, 
						qEntity.orderValue.sum().coalesce(BigDecimal.ZERO).as("sumOrderValue"), 
						qEntity.orderBonusValue.sum().coalesce(BigDecimal.ZERO).as("sumOrderBonusValue"), 
						qEntity.orderTotalCredits.sum().coalesce(BigDecimal.ZERO).as("sumOrderTotalCredits")
				))
		.from(qEntity)
		.where(predicate)
		.fetchOne();
		
		return result;
	}
	
}
