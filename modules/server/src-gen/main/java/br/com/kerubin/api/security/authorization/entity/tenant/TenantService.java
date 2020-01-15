/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TenantService {
	
	public TenantEntity create(TenantEntity tenantEntity);
	
	public TenantEntity read(java.util.UUID id);
	
	public TenantEntity update(java.util.UUID id, TenantEntity tenantEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<TenantEntity> list(TenantListFilter tenantListFilter, Pageable pageable);
	
	public Collection<TenantAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<TenantNameAutoComplete> tenantNameAutoComplete(String query);
}