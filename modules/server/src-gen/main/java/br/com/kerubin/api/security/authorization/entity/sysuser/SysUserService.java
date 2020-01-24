/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

public interface SysUserService {
	
	public SysUserEntity create(SysUserEntity sysUserEntity);
	
	public SysUserEntity read(java.util.UUID id);
	
	public SysUserEntity update(java.util.UUID id, SysUserEntity sysUserEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<SysUserEntity> list(SysUserListFilter sysUserListFilter, Pageable pageable);
	
	public Collection<SysUserAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<SysUserNameAutoComplete> sysUserNameAutoComplete(String query);
}
