package br.com.kerubin.api.customer.payment.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafePositiveVal;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.customer.payment.model.CreditOrder;
import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.security.authorization.OrderStatus;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderEntity;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderService;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final BigDecimal BONUS_DEFAULT_FACTOR = new BigDecimal("0.1"); // 10%
	
	@PersistenceContext
	private EntityManager em;
	
	private BigDecimal bonusFactor;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	@Inject
	private CreditOrderService creditOrderService; 
	
	private BigDecimal getBonusFactor() {
		if (isEmpty(bonusFactor)) {
			return BONUS_DEFAULT_FACTOR;
		}
		else {
			return bonusFactor;
		}
	}
	
	@Transactional
	@Override
	public String createCreditOrder(CreditOrder creditOrder) {
		
		validateCreditOrder(creditOrder);
		
		SysUserEntity user = getContextUser();
		
		String logHeader = MessageFormat.format("user: {0}, tenant: {1}", user.getEmail(), user.getTenant().getName()); 
		
		log.info("Creating payment order for {}...", logHeader);
		
		CreditOrderEntity entity = createCreditOrder(creditOrder, user);
		
		entity = creditOrderService.create(entity);
		
		creditOrder.setId(entity.getId());
		creditOrder.setUserEmail(user.getEmail());
		creditOrder.setOrderValue(entity.getOrderValue());
		
		sendEmail(entity);
		
		log.info("Payment order created for: {}, with data: {}.", logHeader, entity);
		
		String response = buildResponse(user, creditOrder);
		return response;
		
	}

	private String buildResponse(SysUserEntity user, CreditOrder creditOrder) {
		StringBuilder sb = new StringBuilder()
			.append("<p style=\"font-weight: bold; margin-bottom: 20px;\">O pedido de reposição de créditos foi registrado com sucesso ")
			.append(" e gerou o protocolo de identificação <span style=\"color: #FF0000\">")
			.append(creditOrder.getId())
			.append("</span>.<br>Por favor anote essse protocolo para acompanhamento do pedido.")
			.append("<p>O status do pedido de reposição de créditos é <span style=\"color: #FF0000; font-weight: bold\">Aguardando pagamento</span>.</p>")
			.append("<p>Enviamos uma e-mail para ")
			.append("<strong>").append(user.getEmail()).append("</strong>")
			.append(" com os dados deste pedido e da conta bancária para efetuar o pagamento dos créditos.</p>");
		
		return sb.toString();
	}

	private void sendEmail(CreditOrderEntity entity) {
		// TODO Auto-generated method stub
		
	}

	private CreditOrderEntity createCreditOrder(CreditOrder creditOrder, SysUserEntity user) {
		CreditOrderEntity entity = new CreditOrderEntity();
		
		entity.setOrderUser(user);
		entity.setOrderUserName(user.getEmail());
		entity.setOrderTenantName(user.getTenant().getName());
		
		entity.setOrderDate(LocalDate.now());
		entity.setOrderValue(creditOrder.getOrderValue());
		
		BigDecimal bonusValue = getSafePositiveVal(entity.getOrderValue().multiply(getBonusFactor()));
		entity.setOrderBonusValue(bonusValue);
		
		BigDecimal orderTotalCredits = entity.getOrderValue().add(entity.getOrderBonusValue());
		entity.setOrderTotalCredits(orderTotalCredits);
		
		entity.setPaymentMethod(creditOrder.getPaymentMethod());
		entity.setPaymentMethodDescription(creditOrder.getPaymentMethodDescription());
		
		entity.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		return entity;
	}
	
	private void validateCreditOrder(CreditOrder creditOrder) {
		if (isEmpty(creditOrder)) {
			
		}
		
	}

	private void initSession() {
		SysUserEntity user = getContextUser();
		
		UUID tenatId = user.getTenant().getId();
		
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("userFilter");
		filter.setParameter("tenant", tenatId);
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
		session.disableFilter("userFilter");
	}

}
