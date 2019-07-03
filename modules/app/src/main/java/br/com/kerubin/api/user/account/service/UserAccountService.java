package br.com.kerubin.api.user.account.service;

import java.util.UUID;

import javax.validation.Valid;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUser;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.user.account.controller.UserAccount;

public interface UserAccountService {

	SysUserEntity prepareNewAccount(@Valid UserAccount account);

	SysUserEntity createAccount(SysUserEntity user);

	String sendUserAccountConfirmationRequest(SysUserEntity user);

	SysUserEntity confirmUserAccount(String confirmationId);

	TenantEntity createTenantForUser(UUID id);

	String sendChangePasswordLink(String email);

	String changePasswordForgotten(SysUser user);

	String changePassword(SysUser user);

}
