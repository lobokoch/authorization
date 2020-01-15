/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

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
public class CreditOrderServiceImpl implements CreditOrderService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CreditOrderRepository creditOrderRepository;
	
	@Autowired
	private CreditOrderListFilterPredicate creditOrderListFilterPredicate;
	
	
	@Autowired
	private SysUserBaseRepository sysUserBaseRepository;
	
	
	@Transactional
	@Override
	public CreditOrderEntity create(CreditOrderEntity creditOrderEntity) {
		return creditOrderRepository.save(creditOrderEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderEntity read(java.util.UUID id) {
		return getCreditOrderEntity(id);
	}
	
	@Transactional
	@Override
	public CreditOrderEntity update(java.util.UUID id, CreditOrderEntity creditOrderEntity) {
		// CreditOrderEntity entity = getCreditOrderEntity(id);
		// BeanUtils.copyProperties(creditOrderEntity, entity, "id");
		// entity = creditOrderRepository.save(entity);
		
		CreditOrderEntity entity = creditOrderRepository.save(creditOrderEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		creditOrderRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		creditOrderRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CreditOrderEntity> list(CreditOrderListFilter creditOrderListFilter, Pageable pageable) {
		Predicate predicate = creditOrderListFilterPredicate.mountAndGetPredicate(creditOrderListFilter);
		
		Page<CreditOrderEntity> resultPage = creditOrderRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CreditOrderEntity getCreditOrderEntity(java.util.UUID id) {
		Optional<CreditOrderEntity> creditOrderEntity = creditOrderRepository.findById(id);
		if (!creditOrderEntity.isPresent()) {
			throw new IllegalArgumentException("CreditOrder not found:" + id.toString());
		}
		return creditOrderEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CreditOrderAutoComplete> autoComplete(String query) {
		Collection<CreditOrderAutoComplete> result = creditOrderRepository.autoComplete(query);
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
	public Collection<CreditOrderOrderUserNameAutoComplete> creditOrderOrderUserNameAutoComplete(String query) {
		Collection<CreditOrderOrderUserNameAutoComplete> result = creditOrderRepository.creditOrderOrderUserNameAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderSumFields getCreditOrderSumFields(CreditOrderListFilter creditOrderListFilter) {
		Predicate predicate = creditOrderListFilterPredicate.mountAndGetPredicate(creditOrderListFilter);
		
		QCreditOrderEntity qEntity = QCreditOrderEntity.creditOrderEntity;
		JPAQueryFactory query = new JPAQueryFactory(em);
		CreditOrderSumFields result = query.select(
				Projections.bean(CreditOrderSumFields.class, 
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
