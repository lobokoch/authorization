/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;

public interface CreditOrderService {
	
	public CreditOrderEntity create(CreditOrderEntity creditOrderEntity);
	
	public CreditOrderEntity read(java.util.UUID id);
	
	public CreditOrderEntity update(java.util.UUID id, CreditOrderEntity creditOrderEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CreditOrderEntity> list(CreditOrderListFilter creditOrderListFilter, Pageable pageable);
	
	public Collection<CreditOrderAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<SysUserAutoComplete> sysUserOrderUserAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<CreditOrderOrderUserNameAutoComplete> creditOrderOrderUserNameAutoComplete(String query);
	
	public CreditOrderSumFields getCreditOrderSumFields(CreditOrderListFilter creditOrderListFilter);
}
