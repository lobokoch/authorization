/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;

public interface CreditOrderAdminService {
	
	public CreditOrderAdminEntity create(CreditOrderAdminEntity creditOrderAdminEntity);
	
	public CreditOrderAdminEntity read(java.util.UUID id);
	
	public CreditOrderAdminEntity update(java.util.UUID id, CreditOrderAdminEntity creditOrderAdminEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CreditOrderAdminEntity> list(CreditOrderAdminListFilter creditOrderAdminListFilter, Pageable pageable);
	
	public Collection<CreditOrderAdminAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<SysUserAutoComplete> sysUserOrderUserAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<CreditOrderAdminOrderUserNameAutoComplete> creditOrderAdminOrderUserNameAutoComplete(String query);
	
	public Collection<CreditOrderAdminOrderTenantNameAutoComplete> creditOrderAdminOrderTenantNameAutoComplete(String query);
	
	public CreditOrderAdminSumFields getCreditOrderAdminSumFields(CreditOrderAdminListFilter creditOrderAdminListFilter);
}
