package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.customer.payment.exception.CustomerPaymentException;
import br.com.kerubin.api.security.authorization.OrderStatus;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;
import br.com.kerubin.api.security.component.UserHelper;
import br.com.kerubin.api.user.account.exception.UserAccountException;

import static br.com.kerubin.api.servicecore.util.CoreUtils.*; 

@Primary
@Service
public class CustomCreditOrderAdminServiceImpl extends CreditOrderAdminServiceImpl {
	
	@Inject
	private UserHelper userHelper;
	
	@Inject
	private TenantRepository tenantRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Page<CreditOrderAdminEntity> list(CreditOrderAdminListFilter creditOrderAdminListFilter, Pageable pageable) {
		checkOnlySuperAdministratorCanDo();
		return super.list(creditOrderAdminListFilter, pageable);
	}
	
	
	@Transactional
	@Override
	public CreditOrderAdminEntity create(CreditOrderAdminEntity creditOrderAdminEntity) {
		checkOnlySuperAdministratorCanDo();
		
		if (isEmpty(creditOrderAdminEntity.getOrderTenantName())) {
			throw new CustomerPaymentException("O tenant do usuário deve ser informado.");
		}
		
		TenantEntity tenant = tenantRepository.findByNameIgnoreCase(creditOrderAdminEntity.getOrderTenantName());
		validateUserAndTenant(creditOrderAdminEntity, tenant);
		
		if (OrderStatus.PAID.equals(creditOrderAdminEntity.getOrderStatus())) {
			validateOrderForPayment(creditOrderAdminEntity);
		}
		
		CreditOrderAdminEntity entity = super.create(creditOrderAdminEntity);
		
		// Update tenant balance value
		if (OrderStatus.PAID.equals(entity.getOrderStatus()) && isGtZero(entity.getOrderValue())) {
			incTenantCredits(tenant, entity);
			tenantRepository.saveAndFlush(tenant);
		}
		
		return entity;
	}


	private void incTenantCredits(TenantEntity tenant, CreditOrderAdminEntity entity) {
		BigDecimal actualTenantBalance = getSafePositiveValue(tenant.getBalance());
		
		BigDecimal orderValue = getSafePositiveValue(entity.getOrderValue());
		BigDecimal bonusValue = getSafePositiveValue(entity.getOrderBonusValue());
		
		BigDecimal newBalance = actualTenantBalance.add(orderValue).add(bonusValue);
		
		tenant.setBalance(newBalance);
	}
	
	private void decTenantCredits(TenantEntity tenant, CreditOrderAdminEntity entity) {
		BigDecimal actualTenantBalance = getSafePositiveValue(tenant.getBalance());
		
		BigDecimal orderValue = getSafePositiveValue(entity.getOrderValue());
		BigDecimal bonusValue = getSafePositiveValue(entity.getOrderBonusValue());
		
		BigDecimal newBalance = actualTenantBalance.subtract(orderValue).subtract(bonusValue);
		
		tenant.setBalance(newBalance);
	}

	@Transactional
	@Override
	public CreditOrderAdminEntity update(UUID id, CreditOrderAdminEntity creditOrderAdminEntity) {
		checkOnlySuperAdministratorCanDo();
		
		if (isEmpty(creditOrderAdminEntity.getOrderTenantName())) {
			throw new CustomerPaymentException("O tenant do usuário deve ser informado.");
		}
		
		TenantEntity tenant = tenantRepository.findByNameIgnoreCase(creditOrderAdminEntity.getOrderTenantName());
		validateUserAndTenant(creditOrderAdminEntity, tenant);
		
		CreditOrderAdminEntity oldOrder = read(id);
		
		boolean isOldPaid = OrderStatus.PAID.equals(oldOrder.getOrderStatus());
		boolean isNewPaid = OrderStatus.PAID.equals(creditOrderAdminEntity.getOrderStatus());
		
		if (!isOldPaid && isNewPaid) { // Order was paid right now, so must validate
			validateOrderForPayment(creditOrderAdminEntity);
		}
		
		if (isOldPaid && !isNewPaid) {
			validateOrderForNotPayment(creditOrderAdminEntity);
		}
		
		CreditOrderAdminEntity saved = super.update(id, creditOrderAdminEntity);
		
		// Update tenant balance value
		if (!isOldPaid && isNewPaid) {
			incTenantCredits(tenant, saved);
			
			tenantRepository.saveAndFlush(tenant);
		}
		
		// Update tenant balance value
		if (isOldPaid && !isNewPaid) { // Estorna
			decTenantCredits(tenant, saved);
			
			tenantRepository.saveAndFlush(tenant);
		}
		
		return saved;
	}


	private void validateOrderForNotPayment(CreditOrderAdminEntity creditOrderAdminEntity) {
		if (isNotEmpty(creditOrderAdminEntity.getOrderPaidDate())) {
			throw new CustomerPaymentException("A data de pagamento somente deve ser informada quando status for \"PAGO\".");
		}
		
		if (OrderStatus.CANCELED.equals(creditOrderAdminEntity.getOrderStatus())) {
			if (isEmpty(creditOrderAdminEntity.getOrderCanceledDate())) {
				throw new CustomerPaymentException("A data de cancelamento deve ser informada quando status for \"CANCELADO\".");
			}
			
			if (isEmpty(creditOrderAdminEntity.getOrderHistory())) {
				throw new CustomerPaymentException("Um histórico deve ser informada quando status for \"CANCELADO\".");
			}
		}
	}
	
	private void validateOrderForPayment(CreditOrderAdminEntity creditOrderAdminEntity) {
		if (isLteZero(creditOrderAdminEntity.getOrderValue())) {
			throw new CustomerPaymentException("Valor deve ser maior do que zero para status \"PAGO\".");
		}
		
		if (isEmpty(creditOrderAdminEntity.getOrderPaidDate())) {
			throw new CustomerPaymentException("A data de pagamento deve ser informada quando status for \"PAGO\".");
		}
		
		if (creditOrderAdminEntity.getOrderPaidDate().isAfter(LocalDate.now())) {
			throw new CustomerPaymentException("A data de pagamento não pode ser futura quando status for \"PAGO\".");
		}
	}
	
	private void validateUserAndTenant(CreditOrderAdminEntity actual, TenantEntity tenant) {
		if (isEmpty(actual.getOrderUser())) {
			throw new CustomerPaymentException("Usuário deve ser informado.");
		}
		
		if (isEmpty(actual.getOrderTenantName())) {
			throw new CustomerPaymentException("O tenant do usuário deve ser informado.");
		}
		
		if (isEmpty(tenant)) {
			throw new CustomerPaymentException("Usuário não possui tenant.");
		}
		
		if (!actual.getOrderTenantName().equals(tenant.getName())) {
			throw new CustomerPaymentException("O tenant informado para o usuário não confere com o tenant que o usuário possui.");
		}
	}
	
	@Transactional
	@Override
	public void delete(UUID id) {
		checkOnlySuperAdministratorCanDo();
		super.delete(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CreditOrderAdminEntity read(UUID id) {
		checkOnlySuperAdministratorCanDo();
		return super.read(id);
	}
	
	private void checkOnlySuperAdministratorCanDo() {
		SysUserEntity user = userHelper.getContextUser();
		if (user == null) {
			throw new UserAccountException("Contexto sem usuário.");
		}
		
		userHelper.checkOnlySuperAdministratorCanDo(user);
	}

}
