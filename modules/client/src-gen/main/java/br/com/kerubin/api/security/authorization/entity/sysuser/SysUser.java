/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.servicecore.validator.constraint.CpfOrCnpj;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.security.authorization.AccountType;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantLookupResult;

public class SysUser {

	private java.util.UUID id;
	
	@NotBlank(message="\"Nome do usuário\" é obrigatório.")
	@Size(max = 255, message = "\"Nome do usuário\" pode ter no máximo 255 caracteres.")
	private String name;
	
	@Size(max = 20, message = "\"Documento (CNPJ/CPF)\" pode ter no máximo 20 caracteres.")
	@CpfOrCnpj(message="CPF/CNPJ inválido.")
	private String cnpjCPF;
	
	@NotBlank(message="\"E-mail (usuário para login no sistema)\" é obrigatório.")
	@Size(max = 255, message = "\"E-mail (usuário para login no sistema)\" pode ter no máximo 255 caracteres.")
	@Email
	private String email;
	
	@NotBlank(message="\"Senha\" é obrigatório.")
	@Size(max = 255, message = "\"Senha\" pode ter no máximo 255 caracteres.")
	private String password;
	
	@NotBlank(message="\"Confirmação da senha\" é obrigatório.")
	@Size(max = 255, message = "\"Confirmação da senha\" pode ter no máximo 255 caracteres.")
	private String confirmPassword;
	
	private Boolean active = false;
	
	private Boolean administrator = false;
	
	private Boolean superAdministrator = false;
	
	@NotNull(message="\"Tipo da conta\" é obrigatório.")
	private AccountType accountType;
	
	private TenantLookupResult tenant;
	
	private java.time.LocalDateTime activationDate;
	
	private Boolean confirmed = false;
	
	private java.time.LocalDateTime confirmationDate;
	
	@Size(max = 255, message = "\"Identificador da confirmação\" pode ter no máximo 255 caracteres.")
	private String confirmationId;
	
	@Size(max = 255, message = "\"Criado por\" pode ter no máximo 255 caracteres.")
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	@Size(max = 255, message = "\"Alterado por\" pode ter no máximo 255 caracteres.")
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public SysUser() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
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
	
	public TenantLookupResult getTenant() {
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public java.time.LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public java.time.LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
	public void setTenant(TenantLookupResult tenant) {
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
		this.confirmationId = confirmationId;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setCreatedDate(java.time.LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(java.time.LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
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
