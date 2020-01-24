/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

public class Tenant {

	private java.util.UUID id;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	private String name;
	
	@NotNull(message="\"Quantidade máxima de usuários\" é obrigatório.")
	private Long maxUsers;
	
	@NotNull(message="\"Saldo do tenant\" é obrigatório.")
	private java.math.BigDecimal balance;
	
	private Boolean active = false;
	
	
	public Tenant() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getMaxUsers() {
		return maxUsers;
	}
	
	public java.math.BigDecimal getBalance() {
		return balance;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMaxUsers(Long maxUsers) {
		this.maxUsers = maxUsers;
	}
	
	public void setBalance(java.math.BigDecimal balance) {
		this.balance = balance;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenant other = (Tenant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}
