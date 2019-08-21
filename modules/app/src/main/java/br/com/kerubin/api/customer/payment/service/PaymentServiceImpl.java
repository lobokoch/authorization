package br.com.kerubin.api.customer.payment.service;

import static br.com.kerubin.api.servicecore.mail.MailUtils.BR;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_FINANCEIRO;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_SUPORTE;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_NOTIFICADOR;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLFooter;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLHeader;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLSubject;
import static br.com.kerubin.api.servicecore.mail.MailUtils.buildEmptyLine;
import static br.com.kerubin.api.servicecore.mail.MailUtils.toStrong;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getFirstName;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafePositiveValue;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.customer.payment.exception.CustomerPaymentException;
import br.com.kerubin.api.customer.payment.model.Banco;
import br.com.kerubin.api.customer.payment.model.CreditOrder;
import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.security.authorization.OrderStatus;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderEntity;
import br.com.kerubin.api.security.authorization.entity.creditorder.CreditOrderService;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.servicecore.mail.MailSender;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final BigDecimal BONUS_DEFAULT_FACTOR = new BigDecimal("0.1"); // 10%
	
	private static final String BRADESCO = "Bradesco";
	private static final String CPF = "938.517.669-20";
	
	private static Map<String, Banco> bancos;
	
	@PersistenceContext
	private EntityManager em;
	
	private BigDecimal bonusFactor;
	
	@Inject
	private UserAccountRepository sysUserRepository;
	
	@Inject
	private CreditOrderService creditOrderService;
	
	@Inject
	private MailSender mailSender;
	
	public PaymentServiceImpl() {
		bancos = new HashMap<>();
		
		Banco bradesco = Banco.builder()
				.bancoCodigo("237")
				.bancoNome(BRADESCO)
				.agenciaNumero("7225")
				.agenciaDigito("7")
				.contaNumero("0012563")
				.contaDigito("6").build();
		
		Banco cef = Banco.builder()
				.bancoCodigo("104")
				.bancoNome("Caixa Econômica Federal")
				.agenciaNumero("1880")
				.agenciaDigito(null)
				.contaNumero("00002797")
				.contaDigito("5").build();
		
		bancos.put(bradesco.getBancoNome(), bradesco);
		bancos.put(cef.getBancoNome(), cef);
				
	}
	
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
		
		SysUserEntity user = getContextUser();
		validateUser(user);
		validateCreditOrder(creditOrder);
		
		
		String logHeader = MessageFormat.format("user: {0}, tenant: {1}", user.getEmail(), user.getTenant().getName()); 
		
		log.info("Creating payment order for {}...", logHeader);
		
		CreditOrderEntity entity = createCreditOrder(creditOrder, user);
		
		entity = creditOrderService.create(entity);
		
		creditOrder.setId(entity.getId());
		creditOrder.setUserEmail(user.getEmail());
		creditOrder.setOrderValue(entity.getOrderValue());
		
		sendEmail(user, entity);
		
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

	private void sendEmail(SysUserEntity user, CreditOrderEntity entity) {
		String message = buildEmailMessage(user, entity);
		List<String> recipients = Arrays.asList(user.getEmail(), EMAIL_KERUBIN_FINANCEIRO);
		String subsject = "Kerubin - Pedido de reposição de créditos";
		mailSender.sendMail(EMAIL_KERUBIN_NOTIFICADOR, recipients, subsject, message);
	}
	
	private String buildEmailMessage(SysUserEntity user, CreditOrderEntity entity) {
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		Banco banco = bancos.get(entity.getPaymentMethodDescription());
		if (isEmpty(banco)) {
			banco = bancos.get(BRADESCO);
		}
		
		String title = "Kerubin - Pedido de reposição de créditos";
		
		StringBuilder sb = new StringBuilder()
		.append(builEmailHTMLHeader(title, null))
		.append(buildEmptyLine());
		
		String firstName = getFirstName(user.getName());
		String text = "Recebemos seu pedido para reposição de créditos.";
		sb.append(builEmailHTMLSubject(firstName, text))
		.append(buildEmptyLine());
		
		sb.append("<tr>")
		.append("<td align=\"center\" valign=\"middle\">")
		
		.append("O protocolo de identificação do pedido é ")
		.append(toStrong(entity.getId().toString())).append(".").append(BR)
		.append("O valor do pedido é de ")
		.append(toStrong("R$ " + df.format(entity.getOrderValue()))).append(".").append(BR)
		.append("Os dados bancários para fazer o depósito ou a trasferência são:").append(BR)
		.append(banco.toHTML())
		.append("CPF: ").append(toStrong(CPF)).append(BR)
		
		.append("De preferência, faça o depósito ou transferência <strong>identificado</strong> com seu <strong>CPF</strong> ou <strong>CNPJ</strong>.").append(BR)
		.append("Isso agilizará a identificação do pagamento do seu pedido.").append(BR)
		.append("Você pode também nos informar a data em que fez o depósito ou transferência e o número da transação bancária através do e-mail ")
		.append(toStrong(EMAIL_KERUBIN_FINANCEIRO)).append(BR).append(BR)

		.append("Se tiver alguma dúvida, é só entrar em contato com nosso suporte através do e-mail ")
		.append(toStrong(EMAIL_KERUBIN_SUPORTE)).append(".").append(BR).append(BR)

		.append("<span style=\"font-size: 0.8em;\">Em breve teremos mais opções de formas de pagamento para reposição de créditos, como cartão de crédito e boleto bancário.</span>").append(BR);
		
		sb.append("</td>")
		.append("</tr>");

		sb.append(builEmailHTMLFooter());
		
		String result = sb.toString();
		return result; 
	}

	private CreditOrderEntity createCreditOrder(CreditOrder creditOrder, SysUserEntity user) {
		CreditOrderEntity entity = new CreditOrderEntity();
		
		entity.setOrderUser(user);
		entity.setOrderUserName(user.getEmail());
		entity.setOrderTenantName(user.getTenant().getName());
		
		entity.setOrderDate(LocalDate.now());
		entity.setOrderValue(creditOrder.getOrderValue());
		
		BigDecimal bonusValue = getSafePositiveValue(entity.getOrderValue().multiply(getBonusFactor()));
		entity.setOrderBonusValue(bonusValue);
		
		BigDecimal orderTotalCredits = entity.getOrderValue().add(entity.getOrderBonusValue());
		entity.setOrderTotalCredits(orderTotalCredits);
		
		entity.setPaymentMethod(creditOrder.getPaymentMethod());
		entity.setPaymentMethodDescription(creditOrder.getPaymentMethodDescription());
		
		entity.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		return entity;
	}
	
	private void validateUser(SysUserEntity user) {
		if (isEmpty(user.getCnpjCPF())) {
			throw new CustomerPaymentException("O usuário \"" + user.getName() + "\" (" + user.getEmail() + ") não possui CPF/CNPJ cadastrado.");
		}
		
	}
	
	private void validateCreditOrder(CreditOrder creditOrder) {
		if (isEmpty(creditOrder)) {
			throw new CustomerPaymentException("O pedido de reposição de créditos está nulo.");
		}
		
	}

	/*private void initSession() {
		SysUserEntity user = getContextUser();
		
		UUID tenatId = user.getTenant().getId();
		
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("userFilter");
		filter.setParameter("tenant", tenatId);
	}*/
	
	private SysUserEntity getContextUser() {
		SysUserEntity user = sysUserRepository.findByEmailIgnoreCase(ServiceContext.getUser()).orElse(null);
		if (user == null) {
			throw new UserAccountException("Contexto sem usuário.");
		}
		return user;
	}
	
	/*private void endSession() {
		Session session = em.unwrap(Session.class);
		session.disableFilter("userFilter");
	}*/

}
