package br.com.kerubin.api.tenant.billing.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.DELETE;
import static br.com.kerubin.api.servicecore.util.CoreUtils.GET;
import static br.com.kerubin.api.servicecore.util.CoreUtils.POST;
import static br.com.kerubin.api.servicecore.util.CoreUtils.PUT;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafeVal;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isAutoCompleteURL;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isListURL;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isLt;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.tenant.billing.model.BillingConstants.PESSIMISTIC_WRITE;
import static br.com.kerubin.api.tenant.billing.model.BillingConstants.LOCK_TIMEOUT;
import static br.com.kerubin.api.tenant.billing.model.BillingConstants.LOCK_TIMEOUT_VALUE;
import static br.com.kerubin.api.tenant.billing.model.BillingConstants.OPERATION_COST;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.security.authorization.entity.tenant.QTenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.QTenantOpCountEntity;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.TenantOpCountEntity;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.TenantOpCountRepository;
import br.com.kerubin.api.tenant.billing.exception.BillingEnoughCreditsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TenantBillingServiceImpl implements TenantBillingService {
	
	@PersistenceContext
	private EntityManager em;

	@Inject 
	private TenantRepository tenantRepository;	 

	@Inject
	private TenantOpCountRepository tenantOpCount;

	@Override
	@Transactional
	public /*synchronized*/ void computeTenantOperation(String tenant, String username, String requestMethod, String uri) {
		
		String logHeader = MessageFormat.format("[Tenant: {0}, username: {1}, requestMethod: {2}, uri: {3}]", 
				tenant, username, requestMethod, uri);
		
		try {
					
			JPAQueryFactory query = new JPAQueryFactory(em);
			QTenantEntity qTenant = QTenantEntity.tenantEntity;
			
			// System.out.println("Before 1: " + logHeader);
			
			List<TenantEntity> tenants =  query
				.select(qTenant)
				.from(qTenant)
				.where(qTenant.name.toLowerCase().eq(tenant.toLowerCase()))
				.setLockMode(PESSIMISTIC_WRITE)
				.setHint(LOCK_TIMEOUT, LOCK_TIMEOUT_VALUE)
				.fetch();
			
			// System.out.println("After 1: " + logHeader);
		
			if (isNotEmpty(tenants)) {
				TenantEntity tenantEntity = tenants.get(0);
				
				BigDecimal saldoTenant = getSafeVal(tenantEntity.getBalance());
				
				if (isLt(saldoTenant, OPERATION_COST)) {
					log.error("Not enough credits for tenant operation: " + logHeader );
					throw new BillingEnoughCreditsException();
				}
				
				saldoTenant = saldoTenant.subtract(OPERATION_COST);
				tenantEntity.setBalance(saldoTenant);
				
				tenantEntity = tenantRepository.save(tenantEntity);
				
				LocalDate today = LocalDate.now();
				long day = (long) today.getDayOfMonth();
				long month = today.getMonthValue();
				long year = today.getYear();
				long hour = LocalTime.now().getHour();
				
				// System.out.println("Before 2: " + logHeader);
				
				query = new JPAQueryFactory(em);
				QTenantOpCountEntity qTenantOpCount = QTenantOpCountEntity.tenantOpCountEntity;
				List<TenantOpCountEntity> entities = query.select(qTenantOpCount).from(qTenantOpCount).where(
						qTenantOpCount.tenant.id.eq(tenantEntity.getId())
						.and(qTenantOpCount.yearOp.eq(year))
						.and(qTenantOpCount.monthOp.eq(month))
						.and(qTenantOpCount.dayOp.eq(day))
						.and(qTenantOpCount.hourOp.eq(hour))
				)
				.setLockMode(PESSIMISTIC_WRITE)
				.setHint(LOCK_TIMEOUT, LOCK_TIMEOUT_VALUE)
				.fetch();
				
				// System.out.println("After 2: " + logHeader);
				
				TenantOpCountEntity tenantOpCountEntity = null;
				if (isEmpty(entities)) {
					tenantOpCountEntity = new TenantOpCountEntity();
					tenantOpCountEntity.setTenant(tenantEntity);
					tenantOpCountEntity.setYearOp(year);
					tenantOpCountEntity.setMonthOp(month);
					tenantOpCountEntity.setDayOp(day);
					tenantOpCountEntity.setHourOp(hour);
					tenantOpCountEntity.setCountGet(0L);
					tenantOpCountEntity.setCountPost(0L);
					tenantOpCountEntity.setCountPut(0L);
					tenantOpCountEntity.setCountDelete(0L);
					tenantOpCountEntity.setCountList(0L);
					tenantOpCountEntity.setCountAutoComplete(0L);
					tenantOpCountEntity.setCountOp(0L);
				}
				else {
					tenantOpCountEntity = entities.get(0);
				}
				
				tenantOpCountEntity.setDescription(tenantEntity.getName());
				
				switch (requestMethod) {
				case POST:
					tenantOpCountEntity.setCountPost(tenantOpCountEntity.getCountPost() + 1);
					break;
					
				case PUT:
					tenantOpCountEntity.setCountPut(tenantOpCountEntity.getCountPut() + 1);
					break;
					
				case DELETE:
					tenantOpCountEntity.setCountDelete(tenantOpCountEntity.getCountDelete() + 1);
					break;
					
				case GET:
					doGetRequest(tenantOpCountEntity, requestMethod, uri);
					break;

				default:
					tenantOpCountEntity.setCountOp(tenantOpCountEntity.getCountOp() + 1);
					break;
				}
				
				tenantOpCount.save(tenantOpCountEntity);
			} 
		}
		catch(Exception e) {
			log.error("Error at computeBillingForTenant for: " + logHeader + ", error: " + e.getMessage(), e);
			throw e;
		}
		
	}

	private void doGetRequest(TenantOpCountEntity tenantOpCountEntity, String requestMethod, String url) {
		if (isListURL(requestMethod, url)) {
			tenantOpCountEntity.setCountList(tenantOpCountEntity.getCountList() + 1);
		}
		else if (isAutoCompleteURL(requestMethod, url)) {
			tenantOpCountEntity.setCountAutoComplete(tenantOpCountEntity.getCountAutoComplete() + 1);
		}
		else {
			tenantOpCountEntity.setCountGet(tenantOpCountEntity.getCountGet() + 1);
		}
		
	}

}
