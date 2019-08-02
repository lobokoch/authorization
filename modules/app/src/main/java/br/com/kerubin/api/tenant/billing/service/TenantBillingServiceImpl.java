package br.com.kerubin.api.tenant.billing.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.DELETE;
import static br.com.kerubin.api.servicecore.util.CoreUtils.GET;
import static br.com.kerubin.api.servicecore.util.CoreUtils.POST;
import static br.com.kerubin.api.servicecore.util.CoreUtils.PUT;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isAutoCompleteURL;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isListURL;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.security.authorization.entity.tenant.QTenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.QTenantOpCountEntity;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.TenantOpCountEntity;
import br.com.kerubin.api.security.authorization.entity.tenantopcount.TenantOpCountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TenantBillingServiceImpl implements TenantBillingService {

	@PersistenceContext
	private EntityManager em;

	/*
	 * @Inject private TenantRepository tenantRepository;
	 */

	@Inject
	private TenantOpCountRepository tenantOpCount;

	@Override
	@Transactional
	public /*synchronized*/ void computeTenantOperation(String tenant, String username, String requestMethod, String uri) {
		
		try {
			JPAQueryFactory query = new JPAQueryFactory(em);
			QTenantEntity qTenant = QTenantEntity.tenantEntity;
			
			List<TenantEntity> tenants =  query
				.select(qTenant)
				.from(qTenant)
				.where(qTenant.name.toLowerCase().eq(tenant.toLowerCase()))
				.fetch();
		
			if (isNotEmpty(tenants)) {
				TenantEntity tenantEntity = tenants.get(0);
				
				LocalDate today = LocalDate.now();
				long day = (long) today.getDayOfMonth();
				long month = today.getMonthValue();
				long year = today.getYear();
				long hour = LocalTime.now().getHour();
				
				query = new JPAQueryFactory(em);
				QTenantOpCountEntity qTenantOpCount = QTenantOpCountEntity.tenantOpCountEntity;
				List<TenantOpCountEntity> entities = query.select(qTenantOpCount).from(qTenantOpCount).where(
						qTenantOpCount.tenant.id.eq(tenantEntity.getId())
						.and(qTenantOpCount.yearOp.eq(year))
						.and(qTenantOpCount.monthOp.eq(month))
						.and(qTenantOpCount.dayOp.eq(day))
						.and(qTenantOpCount.hourOp.eq(hour))
				)
				.setLockMode(LockModeType.PESSIMISTIC_WRITE)
				.setHint("javax.persistence.lock.timeout", "10000")
				.fetch();
				
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
			log.error("Error at computeBillingForTenant for tenant: " + tenant + ", and user: " + username + ", error: " + e.getMessage(), e);
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
