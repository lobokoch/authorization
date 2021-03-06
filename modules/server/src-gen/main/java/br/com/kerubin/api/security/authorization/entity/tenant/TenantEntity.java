/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

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
	
	public boolean isActive() {
		return Boolean.TRUE.equals(active);
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
			
		
		// Field: id
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id))
			return false;
		
		// Field: name
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name))
			return false;
		
		// Field: maxUsers
		if (maxUsers == null) {
			if (other.maxUsers != null) {
				return false;
			}
		} else if (!maxUsers.equals(other.maxUsers))
			return false;
		
		// Field: balance
		if (balance == null) {
			if (other.balance != null) {
				return false;
			}
		} else if (!balance.equals(other.balance))
			return false;
		
		// Field: active
		if (active == null) {
			if (other.active != null) {
				return false;
			}
		} else if (!active.equals(other.active))
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
