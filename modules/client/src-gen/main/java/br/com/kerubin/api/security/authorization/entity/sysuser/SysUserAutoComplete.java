/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import br.com.kerubin.api.security.authorization.AccountType;

public interface SysUserAutoComplete {

	java.util.UUID getId();
	
	String getName();
	
	String getEmail();
	
	AccountType getAccountType();
	
	void setId(java.util.UUID id);
	
	void setName(String name);
	
	void setEmail(String email);
	
	void setAccountType(AccountType accountType);

}
