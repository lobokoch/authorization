package br.com.kerubin.api.user.account.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;
import br.com.kerubin.api.user.account.controller.UserAccount;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.mail.UserAccountMailer;
import br.com.kerubin.api.user.account.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	private static final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	
	private static final String EMAIL_FROM = "lobokoch@gmail.com";
	private static final String FRONT_END_URL_ = "http://localhost:4200";
	
	
	@Inject
	private UserAccountRepository accountRepository;
	
	@Inject
	private TenantRepository tenantRepository;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Inject
	private UserAccountMailer userAccountMailer;
	
	@Transactional
	@Override
	public SysUserEntity confirmUserAccount(String confirmationId) {
		SysUserEntity user = accountRepository.findByConfirmationId(confirmationId).orElse(null);
		if (user == null) {
			log.error("User account not found for confirmationId: " + confirmationId);
			throw new UserAccountException("Conta não encontrada.");
		}
		
		if (user.getActive()) {
			throw new UserAccountException("A conta já está ativa.");
		}
		
		user.setActive(true);
		user.setConfirmed(true);
		
		LocalDateTime now = LocalDateTime.now();
		user.setConfirmationDate(now);
		user.setActivationDate(now);
		
		// Deteriorates confirmation id without lost original value.
		confirmationId = confirmationId.substring(0, 5) + "_confirmed_" + confirmationId.substring(5);
		confirmationId = "confirmed_" + confirmationId + "_confirmed";
		user.setConfirmationId(confirmationId);
		
		TenantEntity tenant = createTenantForUser(user);
		user.setTenant(tenant);
		
		user = accountRepository.save(user);
		
		return user;
	}
	
	@Override
	public String sendChangePasswordLink(String email) {
		SysUserEntity user = accountRepository.findByEmailIgnoreCase(email).orElse(null);
		if (user == null) {
			throw new UserAccountException("Conta inexistente para o e-mail \"" + email + "\".");
		}
		
		String result = doSendChangePasswordLink(user);
		
		return result;
	}
	
	@Override
	public SysUserEntity prepareNewAccount(@Valid UserAccount account) {
		SysUserEntity user = accountRepository.findByEmailIgnoreCase(account.getEmail()).orElse(null);
		if (user != null) {
			throw new UserAccountException("Já existe uma conta criada com o e-mail \"" + account.getEmail() + "\".");
		}
		
		user = new SysUserEntity();
		user.setName(account.getName());
		user.setEmail(account.getEmail());
		
		String pwdEncoded = passwordEncoder.encode(account.getPassword());
		user.setPassword(pwdEncoded);
		user.setAccountType(account.getAccountType());
		
		user.setAdministrator(false);
		user.setActive(false);
		user.setConfirmed(false);
		user.setConfirmationId(UUID.randomUUID().toString());
		
		return user;
	}
	
	@Transactional
	@Override
	public SysUserEntity createAccount(SysUserEntity user) {
		SysUserEntity created = accountRepository.save(user);
		return created;
	}
	
	public String doSendChangePasswordLink(SysUserEntity user) {
		String from = EMAIL_FROM;
		List<String> recipients = Arrays.asList(user.getEmail());
		String subsject = "Kerubin - Redefinir senha da conta";
		String message = buildChangePasswordLinkMessage(user);
		
		// Sends e-mail in another thread to response faster to the user.
		CompletableFuture.runAsync(() -> {
			userAccountMailer.sendMail(from, recipients, subsject, message);
		});
		
		String result = buildSendChangePasswordLinkResponse(user.getName(), user.getEmail()); 
		return result;
	}

	private String buildSendChangePasswordLinkResponse(String name, String email) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append("<p>")
		.append("<strong>")
		.append(getFirstName(name))
		.append("</strong>")
		.append(", sua solicitação para redefinição de senha foi recebida com sucesso!</p>");

		sb.append("<p>Enviamos um e-mail para ")
		.append("<strong>")
		.append(email)
		.append("</strong>")
		.append(" com instruções para redefinir a senha.</p>");
		
		return sb.toString();
	}

	private String buildChangePasswordLinkMessage(SysUserEntity user) {
		String confirmationBaseURL = FRONT_END_URL_ + "/changepassword";
		StringBuilder sb = new StringBuilder();
		
		String url = confirmationBaseURL + "?id=" + user.getId() + "&email=" + user.getEmail();
		
		sb
		.append("<p>")
		.append(getFirstName(user.getName()))
		.append(", aqui está o link solicitado para você redefinir sua senha. Caso não tenha sido você que solicitou, ignore esse e-mail.</p>");

		sb
		.append("<h3>Clique <strong><a href=\"")
		.append(url)
		.append("\">aqui</a></strong> para redefinir a senha da sua conta.</h3>");

		sb.append("<p>Atenciosamente,</p>");

		sb.append("<p>Equipe Kerubin.</p>");
		
		return sb.toString();
	}

	@Override
	public String sendUserAccountConfirmationRequest(SysUserEntity user) {
		String from = EMAIL_FROM;
		List<String> recipients = Arrays.asList(user.getEmail());
		String subsject = "Kerubin - Vamos finalizar a criação da sua conta";
		String message = buildConfirmationMessage(user);
		
		// Sends e-mail in another thread to response faster to the user.
		CompletableFuture.runAsync(() -> {
			userAccountMailer.sendMail(from, recipients, subsject, message);
		});
		
		/*Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			userAccountMailer.sendMail(from, recipients, subsject, message);
		});*/
		
		String result = buildResultAccountConfirmationRequest(user.getName(), user.getEmail()); 
		return result;
	}

	private String buildResultAccountConfirmationRequest(String name, String email) {
		StringBuilder sb = new StringBuilder();
		
		sb
		.append("<h2>Parab&eacute;ns ")
		.append(getFirstName(name))
		.append(", sua conta foi criada com sucesso!</h2>");

		sb.append("<p>Por segurança, enviamos um e-mail para ")
		.append("<strong>")
		.append(email)
		.append("</strong>")
		.append(" confirmar a conta.</p>");

		sb.append("<p>Assim que a conta for confirmada, ela poderá ser usada.</p>");
		
		return sb.toString();
	}

	private String buildConfirmationMessage(SysUserEntity user) {
		String confirmationBaseURL = FRONT_END_URL_ + "/confirmaccount";
		StringBuilder sb = new StringBuilder();
		
		String url = confirmationBaseURL + "?id=" + user.getConfirmationId();
		
		sb
		.append("<h2>")
		.append(getFirstName(user.getName()))
		.append(", falta muito pouco para voc&ecirc; finalizar a cria&ccedil;&atilde;o da sua conta no Kerubin.</h2>");

		sb
		.append("<h3>Clique <strong><a href=\"")
		.append(url)
		.append("\">aqui</a></strong> para confirmar a cria&ccedil;&atilde;o da sua conta.</h3>");

		sb.append("<p>Atenciosamente,</p>");

		sb.append("<p>Equipe Kerubin.</p>");
		
		return sb.toString();
	}

	private String getFirstName(String fullName) {
		String firstName = fullName.substring(0, fullName.indexOf(' ')).trim();
		if (firstName.isEmpty()) {
			firstName = fullName;
		}
		
		return firstName;
	}
	
	@Transactional
	@Override
	public TenantEntity createTenantForUser(UUID id) {
		SysUserEntity user = accountRepository.findById(id).orElse(null);
		
		if (user == null) {
			throw new UserAccountException("Usuário não encontrado com o id: " + id + ".");
		}
		
		if (isNotEmpty(user.getTenant())) {
			throw new UserAccountException("Usuário id: " + id + " já possui tenant associado.");
		}
		
		if (isEmpty(user.getEmail())) {
			throw new UserAccountException("Usuário id: " + id + " não possui e-mail.");
		}
		
		TenantEntity tenant = createTenantForUser(user);
		user.setTenant(tenant);
		
		accountRepository.save(user);
		
		return tenant;
	}
	
	

	private TenantEntity createTenantForUser(SysUserEntity user) {
		TenantEntity tenant = new TenantEntity();
		String tenantName = user.getEmail().replace(".", "").replace("@", "");
		tenant.setName(tenantName);
		tenant.setActive(true);
		
		tenant = tenantRepository.save(tenant);
		return tenant;
	}

	private boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		
		if (value instanceof String) {
			String str = (String) value;
			return str.trim().isEmpty();
		}
		
		if (value instanceof Collection) {
			Collection<?> collection = (Collection<?>) value;
			return collection.isEmpty();
		}
		
		return false;
	}
	
	private boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	

}
 