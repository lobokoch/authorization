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
		
		validateUserAndTenant(creditOrderAdminEntity);
		
		if (OrderStatus.PAID.equals(creditOrderAdminEntity.getOrderStatus())) {
			validateOrderForPayment(creditOrderAdminEntity);
		}
		
		CreditOrderAdminEntity entity = super.create(creditOrderAdminEntity);
		
		// Update tenant balance value
		if (OrderStatus.PAID.equals(entity.getOrderStatus()) && isGtZero(entity.getOrderValue())) {
			TenantEntity tenant = entity.getOrderUser().getTenant();
			BigDecimal balance = getSafePositiveValue(tenant.getBalance());
			balance = balance.add(entity.getOrderValue());
			tenant.setBalance(balance);
			tenantRepository.saveAndFlush(tenant);
		}
		
		return entity;
	}

	@Transactional
	@Override
	public CreditOrderAdminEntity update(UUID id, CreditOrderAdminEntity creditOrderAdminEntity) {
		checkOnlySuperAdministratorCanDo();
		
		validateUserAndTenant(creditOrderAdminEntity);
		
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
			TenantEntity tenant = saved.getOrderUser().getTenant();
			BigDecimal balance = getSafePositiveValue(tenant.getBalance());
			balance = balance.add(saved.getOrderValue());
			tenant.setBalance(balance);
			tenantRepository.saveAndFlush(tenant);
		}
		
		// Update tenant balance value
		if (isOldPaid && !isNewPaid) { // Estorna
			TenantEntity tenant = saved.getOrderUser().getTenant();
			BigDecimal balance = getSafePositiveValue(tenant.getBalance());
			balance = getSafePositiveValue(balance.subtract(saved.getOrderValue()));
			tenant.setBalance(balance);
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
	
	private void validateUserAndTenant(CreditOrderAdminEntity creditOrderAdminEntity) {
		if (isEmpty(creditOrderAdminEntity.getOrderUser())) {
			throw new CustomerPaymentException("Usuário deve ser informado.");
		}
		
		if (isEmpty(creditOrderAdminEntity.getOrderUser().getTenant())) {
			throw new CustomerPaymentException("Usuário não possui tenant.");
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
