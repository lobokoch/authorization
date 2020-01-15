/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tenant")
public class TenantEntity  {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	@Column(name="name")
	private String name;
	
	@NotNull(message="\"Quantidade máxima de usuários\" é obrigatório.")
	@Column(name="max_users")
	private Long maxUsers;
	
	@NotNull(message="\"Saldo do tenant\" é obrigatório.")
	@Column(name="balance")
	private java.math.BigDecimal balance;
	
	@Column(name="active")
	private Boolean active = false;
	
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
		this.name = name != null ? name.trim() : name; // Chamadas REST fazem trim.
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
	
	public void assign(TenantEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setName(source.getName());
			this.setMaxUsers(source.getMaxUsers());
			this.setBalance(source.getBalance());
			this.setActive(source.getActive());
		}
	}
	
	public TenantEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public TenantEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (TenantEntity) visited.get(this);
		}
				
		TenantEntity theClone = new TenantEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setName(this.getName());
		theClone.setMaxUsers(this.getMaxUsers());
		theClone.setBalance(this.getBalance());
		theClone.setActive(this.getActive());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantEntity other = (TenantEntity) obj;
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
