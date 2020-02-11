package br.com.kerubin.api.customer.payment.service;

import static br.com.kerubin.api.servicecore.mail.MailUtils.BR;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_FINANCEIRO;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_FINANCEIRO_APP_PWD;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_FINANCEIRO_PERSONAL;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_SUPORTE;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_LOBOKOCH;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLFooter;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLHeader;
import static br.com.kerubin.api.servicecore.mail.MailUtils.builEmailHTMLSubject;
import static br.com.kerubin.api.servicecore.mail.MailUtils.buildEmptyLine;
import static br.com.kerubin.api.servicecore.mail.MailUtils.toStrong;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatMoney;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getFirstName;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafePositiveValue;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;

import java.math.BigDecimal;
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
import br.com.kerubin.api.servicecore.mail.MailInfo;
import br.com.kerubin.api.servicecore.mail.MailSender;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final BigDecimal BONUS_DEFAULT_FACTOR = new BigDecimal("0.1"); // 10%
	private static final String PIC_PAY = "PicPay";
	private static final String KERUBIN_HTML = "<span style=\"color: #1e94d2; font-weight: bold;\">Kerubin</span>";
	
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
			.append("<p style=\"font-weight: bold; margin-bottom: 20px;\">O seu pedido de reposição de créditos foi registrado com sucesso ")
			.append(" e gerou o protocolo de identificação <span style=\"color: #FF0000\">")
			.append(creditOrder.getId())
			.append("</span>.<br>Por favor anote essse protocolo para acompanhamento do pedido.")
			.append("<p>O status do pedido de reposição de créditos é <span style=\"color: #FF0000; font-weight: bold\">Aguardando pagamento</span>.</p>")
			.append("<p>Enviamos uma e-mail para ")
			.append("<strong>").append(user.getEmail()).append("</strong>")
			.append(" com os dados deste pedido e mais informações para efetuar o pagamento dos créditos.</p>");
		
		return sb.toString();
	}

	private void sendEmail(SysUserEntity user, CreditOrderEntity entity) {
		String message = buildEmailMessage(user, entity);
		
		MailInfo loboKoch = new MailInfo(EMAIL_LOBOKOCH, "Kerubin Financeiro", null);
		MailInfo from = new MailInfo(EMAIL_KERUBIN_FINANCEIRO, EMAIL_KERUBIN_FINANCEIRO_PERSONAL, EMAIL_KERUBIN_FINANCEIRO_APP_PWD);
		MailInfo toUser = new MailInfo(user.getEmail(), user.getName(), null);		
		
		List<MailInfo> recipients = Arrays.asList(toUser, loboKoch, from);
		String subject = "Kerubin - Pedido de reposição de créditos";
		
		mailSender.sendMailBcc(from, recipients, subject, message);
		
		//mailSender.sendMail(EMAIL_KERUBIN_NOTIFICADOR, recipients, subsject, message);
	}
	
	private String buildEmailMessage(SysUserEntity user, CreditOrderEntity entity) {
		Banco banco = null;
		boolean isPicPay = PIC_PAY.equalsIgnoreCase(entity.getPaymentMethodDescription());
		if (!isPicPay) {
			banco = bancos.get(entity.getPaymentMethodDescription());
		}
		if (!isPicPay && isEmpty(banco)) {
			isPicPay = true;
		}
		
		String title = "Kerubin - Pedido de reposição de créditos";
		
		StringBuilder sb = new StringBuilder()
		.append(builEmailHTMLHeader(title, " td { padding: 15px; } "))
		.append(buildEmptyLine());
		
		String firstName = getFirstName(user.getName());
		String text = "Recebemos seu pedido para reposição de créditos.";
		sb.append(builEmailHTMLSubject(firstName, text))
		.append(buildEmptyLine());
		
		sb.append("<tr>");
		if (isPicPay) {
			sb.append("<td>");
		}
		else {
			sb.append("<td align=\"center\" valign=\"middle\">");
		}
		
		sb.append("O protocolo de identificação do pedido é ")
		.append(toStrong(entity.getId().toString())).append(".").append(BR)
		.append("O valor do pedido é de ")
		.append(toStrong(formatMoney(entity.getOrderValue()))).append(".").append(BR);
		
		if (isPicPay) {
			sb.append("O PicPay do ").append(KERUBIN_HTML).append(" é <strong>@kerubin.com.br</strong>.")
			.append("<p>Nota: caso você ainda não tenha o aplicativo <span style=\"color: #48c971; font-weight: bold;\">PicPay</span> instalado no seu celular, ")
			.append("acesse <a href=\"https://www.picpay.com/site\">www.picpay.com/site</a> e clique em <strong>Baixe o app</strong> para instalá-lo.</p>")
			.append("<p>Procedimentos para relalizar o pagamento pelo aplicativo <span style=\"color: #48c971; font-weight: bold;\">PicPay</span>:</p>")
			
			.append("<ul>")
			.append("<li>")
			.append("No seu telefone celular, toque no ícone do <span style=\"color: #48c971; font-weight: bold;\">PicPay</span> para abrir o aplicativo.")
			.append("</li>")
			
			.append("<li>")
			.append("Após o aplicativo PicPay abrir, toque na opção <strong>Pagar</strong>, fica na parte mais abaixo do aplicativo.")
			.append("</li>")
			
			.append("<li>")
			.append("Digite <strong>@kerubin.com.br</strong> na caixa de texto \"Quem você quer pagar?\", localizada na parte superior do aplicativo.")
			.append("</li>")
			
			.append("<li>")
			//.append("<span>Deve aparecer apenas a opção <strong>@kerubin.com.br</strong>, com a logo do Kerubin posicionada a esquerda (K com asas em cor branca e fundo azul).")
			.append("<p>Deve aparecer apenas a opção <strong>@kerubin.com.br</strong>, semelhante a imagem abaixo:</p>")
			
			.append("<div>")
			.append("<img src=\"https://www.kerubin.com.br/assets/images/picpay_kerubin.png\">")
			.append("</div>")
			
			.append("</li>")
			
			.append("<li>")
			.append("Toque na opção <strong>@kerubin.com.br</strong>.")
			.append("</li>")
			
			.append("<li>")
			.append("Digite o valor: ").append(toStrong(formatMoney(entity.getOrderValue())))
			.append("</li>")
			
			.append("<li>")
			.append("Toque na opção <strong>Pagar</strong>.")
			.append("</li>")
			
			.append("<li>")
			.append("Siga os demais procedimentos solicitados pelo aplicativo PicPay até que ele confirme que seu pagamento foi realizado com sucesso e pronto!")
			.append("</li>")
			
			.append("</ul>");
		}
		else {
			sb.append("Os dados bancários para fazer o depósito ou a trasferência são:").append(BR)
			.append(banco.toHTML())
			.append("CPF: ").append(toStrong(CPF)).append(BR)
			
			.append("De preferência, faça o depósito ou transferência <strong>identificado</strong> com seu <strong>CPF</strong> ou <strong>CNPJ</strong>.").append(BR)
			.append("Isso agilizará a identificação do pagamento do seu pedido.").append(BR)
			.append("Você pode também nos informar a data em que fez o depósito ou transferência e o número da transação bancária através do e-mail ")
			.append(toStrong(EMAIL_KERUBIN_FINANCEIRO)).append(BR).append(BR);			
		}

		sb.append("Se tiver alguma dúvida, é só entrar em contato com nosso suporte através do e-mail ")
		.append(toStrong(EMAIL_KERUBIN_SUPORTE)).append(".").append(BR).append(BR);

		//.append("<span style=\"font-size: 0.8em;\">Em breve teremos mais opções de formas de pagamento para reposição de créditos, como cartão de crédito e boleto bancário.</span>").append(BR);
		
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
