/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

public interface TenantOpCountService {
	
	public TenantOpCountEntity create(TenantOpCountEntity tenantOpCountEntity);
	
	public TenantOpCountEntity read(java.util.UUID id);
	
	public TenantOpCountEntity update(java.util.UUID id, TenantOpCountEntity tenantOpCountEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<TenantOpCountEntity> list(TenantOpCountListFilter tenantOpCountListFilter, Pageable pageable);
	
	public Collection<TenantOpCountAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(String query);
	// End relationships autoComplete
	 
}
