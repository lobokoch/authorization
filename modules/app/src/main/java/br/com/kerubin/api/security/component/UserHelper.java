package br.com.kerubin.api.security.component;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;

public interface UserHelper {
	
	long getNumberOfUsersForTenant(TenantEntity tenant);

	long getMaxConfiguredUsersForTenant(SysUserEntity user);

	void checkMaxUsersForTenantOnUserCreation(SysUserEntity user);

}
