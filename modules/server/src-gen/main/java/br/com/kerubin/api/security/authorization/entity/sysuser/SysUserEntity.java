/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import br.com.kerubin.api.servicecore.annotation.Password;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Filter;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.servicecore.validator.constraint.CpfOrCnpj;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.security.authorization.AccountType;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "sys_user")

@FilterDef(name = "userFilter", parameters = {
	@ParamDef(name = "tenant", type = "java.util.UUID")
})


@Filters( {
    @Filter(name = "userFilter", condition = ":tenant = tenant")
} )

public class SysUserEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Nome do usuário\" é obrigatório.")
	@Size(max = 255, message = "\"Nome do usuário\" pode ter no máximo 255 caracteres.")
	@Column(name="name")
	private String name;
	
	@Size(max = 20, message = "\"Documento (CNPJ/CPF)\" pode ter no máximo 20 caracteres.")
	@CpfOrCnpj(message="CPF/CNPJ inválido.")
	@Column(name="cnpj_cpf")
	private String cnpjCPF;
	
	@NotBlank(message="\"E-mail (usuário para login no sistema)\" é obrigatório.")
	@Size(max = 255, message = "\"E-mail (usuário para login no sistema)\" pode ter no máximo 255 caracteres.")
	@Email
	@Column(name="email")
	private String email;
	
	@NotBlank(message="\"Senha\" é obrigatório.")
	@Size(max = 255, message = "\"Senha\" pode ter no máximo 255 caracteres.")
	@Password
	@Column(name="password")
	private String password;
	
	@Password
	@Transient
	private String confirmPassword;
	
	@Column(name="active")
	private Boolean active = false;
	
	@Column(name="administrator")
	private Boolean administrator = false;
	
	@Column(name="super_administrator")
	private Boolean superAdministrator = false;
	
	@NotNull(message="\"Tipo da conta\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private AccountType accountType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant")
	private TenantEntity tenant;
	
	@Column(name="activation_date")
	private java.time.LocalDateTime activationDate;
	
	@Column(name="confirmed")
	private Boolean confirmed = false;
	
	@Column(name="confirmation_date")
	private java.time.LocalDateTime confirmationDate;
	
	@Size(max = 255, message = "\"Identificador da confirmação\" pode ter no máximo 255 caracteres.")
	@Column(name="confirmation_id")
	private String confirmationId;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCnpjCPF() {
		return cnpjCPF;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public Boolean getAdministrator() {
		return administrator;
	}
	
	public Boolean getSuperAdministrator() {
		return superAdministrator;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public TenantEntity getTenant() {
		return tenant;
	}
	
	public java.time.LocalDateTime getActivationDate() {
		return activationDate;
	}
	
	public Boolean getConfirmed() {
		return confirmed;
	}
	
	public java.time.LocalDateTime getConfirmationDate() {
		return confirmationDate;
	}
	
	public String getConfirmationId() {
		return confirmationId;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name != null ? name.trim() : name; // Chamadas REST fazem trim.
	}
	
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF != null ? cnpjCPF.trim() : cnpjCPF; // Chamadas REST fazem trim.
	}
	
	public void setEmail(String email) {
		this.email = email != null ? email.trim() : email; // Chamadas REST fazem trim.
	}
	
	public void setPassword(String password) {
		this.password = password != null ? password.trim() : password; // Chamadas REST fazem trim.
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword != null ? confirmPassword.trim() : confirmPassword; // Chamadas REST fazem trim.
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}
	
	public void setSuperAdministrator(Boolean superAdministrator) {
		this.superAdministrator = superAdministrator;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setTenant(TenantEntity tenant) {
		this.tenant = tenant;
	}
	
	public void setActivationDate(java.time.LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}
	
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public void setConfirmationDate(java.time.LocalDateTime confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	
	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId != null ? confirmationId.trim() : confirmationId; // Chamadas REST fazem trim.
	}
	
	public void assign(SysUserEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setName(source.getName());
			this.setCnpjCPF(source.getCnpjCPF());
			this.setEmail(source.getEmail());
			this.setPassword(source.getPassword());
			this.setConfirmPassword(source.getConfirmPassword());
			this.setActive(source.getActive());
			this.setAdministrator(source.getAdministrator());
			this.setSuperAdministrator(source.getSuperAdministrator());
			this.setAccountType(source.getAccountType());
			this.setTenant(source.getTenant());
			this.setActivationDate(source.getActivationDate());
			this.setConfirmed(source.getConfirmed());
			this.setConfirmationDate(source.getConfirmationDate());
			this.setConfirmationId(source.getConfirmationId());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
		}
	}
	
	public SysUserEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public SysUserEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (SysUserEntity) visited.get(this);
		}
				
		SysUserEntity theClone = new SysUserEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setName(this.getName());
		theClone.setCnpjCPF(this.getCnpjCPF());
		theClone.setEmail(this.getEmail());
		theClone.setPassword(this.getPassword());
		theClone.setConfirmPassword(this.getConfirmPassword());
		theClone.setActive(this.getActive());
		theClone.setAdministrator(this.getAdministrator());
		theClone.setSuperAdministrator(this.getSuperAdministrator());
		theClone.setAccountType(this.getAccountType());
		theClone.setTenant(this.getTenant() != null ? this.getTenant().clone(visited) : null);
		theClone.setActivationDate(this.getActivationDate());
		theClone.setConfirmed(this.getConfirmed());
		theClone.setConfirmationDate(this.getConfirmationDate());
		theClone.setConfirmationId(this.getConfirmationId());
		theClone.setCreatedBy(this.getCreatedBy());
		theClone.setCreatedDate(this.getCreatedDate());
		theClone.setLastModifiedBy(this.getLastModifiedBy());
		theClone.setLastModifiedDate(this.getLastModifiedDate());
		
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
		SysUserEntity other = (SysUserEntity) obj;
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
