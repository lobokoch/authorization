package br.com.kerubin.api.customer.payment.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.database.entity.QAuditingEntity;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderEntity;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderListFilter;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderServiceImpl;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderSumFields;
import br.com.kerubin.api.security.authorization.entity.creditorder.QCreditOrderEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.servicecore.error.ForbiddenOperationException;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;

@Primary
@Service
public class CustomCreditOrderServiceImpl extends CreditOrderServiceImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	/*@Inject
	private UserHelper userHelper;*/
	
	private static String lastModifiedDateFieldName;
	private static String idFieldName;
	
	public CustomCreditOrderServiceImpl() {
		lastModifiedDateFieldName = QAuditingEntity.auditingEntity.lastModifiedDate.getMetadata().getName();
		idFieldName = QCreditOrderEntity.creditOrderEntity.id.getMetadata().getName();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<CreditOrderEntity> list(CreditOrderListFilter creditOrderListFilter, Pageable pageable) {

		SysUserEntity user = getContextUser();
		onlyAdministratorCanDo(user);
		
		initSession();
		try {
			
			boolean isOrderById = pageable.getSort().stream().anyMatch(order -> idFieldName.equals(order.getProperty()));
			if (isOrderById) {
				Sort defaultSort = Sort.by(lastModifiedDateFieldName).descending();
				pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), defaultSort);
			}
			
			return super.list(creditOrderListFilter, pageable);
		}
		finally {
			endSession();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderSumFields getCreditOrderSumFields(CreditOrderListFilter creditOrderListFilter) {
		SysUserEntity user = getContextUser();
		onlyAdministratorCanDo(user);
		
		initSession();
		try {
			return super.getCreditOrderSumFields(creditOrderListFilter);
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
	
	private void initSession() {
		SysUserEntity user = getContextUser();
		
		String orderTenantName = user.getTenant().getName();
		
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("creditOrder");
		filter.setParameter("orderTenantName", orderTenantName);
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
		session.disableFilter("creditOrder");
	}

}
