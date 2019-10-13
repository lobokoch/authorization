package br.com.kerubin.api.user.account.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUser;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserDTOConverter;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.Tenant;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantDTOConverter;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.user.account.exception.UserAccountException;
import br.com.kerubin.api.user.account.model.AccountCreatedDTO;
import br.com.kerubin.api.user.account.model.SimpleTextDTO;
import br.com.kerubin.api.user.account.service.UserAccountService;

@RestController
@RequestMapping("/security/authorization/account")
public class UserAccountController {
	
	private static final Logger log = LoggerFactory.getLogger(UserAccountController.class);
	
	@Inject
	private UserAccountService userAccountService;
	
	@Inject
	SysUserDTOConverter sysUserDTOConverter;
	
	@Inject
	TenantDTOConverter tenantDTOConverter;
	
	@PostMapping("/createAccount")
	public ResponseEntity<AccountCreatedDTO> createAccount(@Valid @RequestBody UserAccount account) {
		try {
			SysUserEntity user = userAccountService.prepareNewAccount(account);
			SysUserEntity createdUser = userAccountService.createAccount(user);
			String text = userAccountService.sendUserAccountConfirmationRequest(createdUser);
			AccountCreatedDTO response = new AccountCreatedDTO(text);
			return ResponseEntity.ok(response);
		} catch(UserAccountException e) {
			log.error("Error creating new account for: " + account, e);
			throw e;
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch(Exception e) {
			log.error("Error creating new account for: " + account, e);
			throw e;
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Desculpe-nos, houve um erro inesperado ao tentar criar a conta. Tente novamente daqui a alguns instantes.");
		}
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<SimpleTextDTO> changePassword(@RequestBody SysUser user) {
		try {
			String text = userAccountService.changePassword(user);
			SimpleTextDTO dto = new SimpleTextDTO(text);
			return ResponseEntity.ok(dto);
		} catch(UserAccountException e) {
			log.error("Error at changePassword for: " + user, e);
			SimpleTextDTO dto = new SimpleTextDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		} catch(Exception e) {
			log.error("Error at changePassword: " + user, e);
			throw e;
		}
	}
	
	@PostMapping("/changePasswordForgotten")
	public ResponseEntity<SimpleTextDTO> changePasswordForgotten(@RequestBody SysUser user) {
		try {
			String text = userAccountService.changePasswordForgotten(user);
			SimpleTextDTO dto = new SimpleTextDTO(text);
			return ResponseEntity.ok(dto);
		} catch(UserAccountException e) {
			log.error("Error at changePasswordForgotten for: " + user, e);
			SimpleTextDTO dto = new SimpleTextDTO(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		} catch(Exception e) {
			log.error("Error at changePasswordForgotten: " + user, e);
			throw e;
		}
	}
	
	@PostMapping("/sendChangePasswordLink")
	public ResponseEntity<SimpleTextDTO> sendChangePasswordLink(@RequestBody String email) {
		try {
			String text = userAccountService.sendChangePasswordLink(email);
			SimpleTextDTO dto = new SimpleTextDTO(text);
			return ResponseEntity.ok(dto);
		} catch(UserAccountException e) {
			log.error("Error sending link for: " + email, e);
			throw e;
		} catch(Exception e) {
			log.error("Error for: " + email, e);
			throw e;
		}
	}
	
	@Transactional
	@PutMapping("/confirmAccount/{id}")
	public ResponseEntity<SysUser> confirmAccount(@PathVariable String id) {
		try {
			SysUserEntity user = userAccountService.confirmUserAccount(id);
			SysUser dto = sysUserDTOConverter.convertEntityToDto(user);
			dto.setPassword(null);
			dto.setConfirmationId(null);
			return ResponseEntity.ok(dto);
		} catch(UserAccountException e) {
			log.error("Error at confirmAccount for confirmationId: " + id, e);
			throw e;
		} catch(Exception e) {
			log.error("Error at confirmAccount for confirmationId:" + id, e);
			throw e;
		}
	}
	
	@Transactional
	@PutMapping("/createTenantForUser/{id}")
	public ResponseEntity<Tenant> createTenantForUser(@PathVariable java.util.UUID id) {
		try {
			TenantEntity tenantEntity = userAccountService.createTenantForUser(id);
			Tenant tenant = tenantDTOConverter.convertEntityToDto(tenantEntity);
			return ResponseEntity.ok(tenant);
		} catch(UserAccountException e) {
			log.error("Error at createTenantForUser for user id: " + id, e);
			throw e;
		} catch(Exception e) {
			log.error("Error at setTenantForUser for user id: " + id, e);
			throw e;
		}
	}
	
	

}
