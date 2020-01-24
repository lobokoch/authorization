/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import lombok.Getter;
import lombok.Setter;
import br.com.kerubin.api.security.authorization.AccountType;

@Getter @Setter
public class SysUserAutoCompleteImpl implements SysUserAutoComplete {

	private java.util.UUID id;
	
	private String name;
	
	private String email;
	
	private AccountType accountType;
	
	public SysUserAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
