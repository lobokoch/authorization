/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantLookupResult;

public class TenantOpCount {

	private java.util.UUID id;
	
	@Size(max = 255, message = "\"description\" pode ter no máximo 255 caracteres.")
	private String description;
	
	@NotNull(message="\"Tenant\" é obrigatório.")
	private TenantLookupResult tenant;
	
	@NotNull(message="\"Ano da operação\" é obrigatório.")
	private Long yearOp;
	
	@NotNull(message="\"Mês da operação\" é obrigatório.")
	private Long monthOp;
	
	@NotNull(message="\"Dia da operação\" é obrigatório.")
	private Long dayOp;
	
	@NotNull(message="\"Hora da operação\" é obrigatório.")
	private Long hourOp;
	
	@NotNull(message="\"Quantidade de operações GET simples\" é obrigatório.")
	private Long countGet;
	
	@NotNull(message="\"Quantidade de operações POST\" é obrigatório.")
	private Long countPost;
	
	@NotNull(message="\"Quantidade de operações PUT\" é obrigatório.")
	private Long countPut;
	
	@NotNull(message="\"Quantidade de operações DELETE\" é obrigatório.")
	private Long countDelete;
	
	@NotNull(message="\"Quantidade de operações LIST\" é obrigatório.")
	private Long countList;
	
	@NotNull(message="\"Quantidade de operações AUTO COMPLETE\" é obrigatório.")
	private Long countAutoComplete;
	
	@NotNull(message="\"Quantidade de outras operações\" é obrigatório.")
	private Long countOp;
	
	
	public TenantOpCount() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TenantLookupResult getTenant() {
		return tenant;
	}
	
	public Long getYearOp() {
		return yearOp;
	}
	
	public Long getMonthOp() {
		return monthOp;
	}
	
	public Long getDayOp() {
		return dayOp;
	}
	
	public Long getHourOp() {
		return hourOp;
	}
	
	public Long getCountGet() {
		return countGet;
	}
	
	public Long getCountPost() {
		return countPost;
	}
	
	public Long getCountPut() {
		return countPut;
	}
	
	public Long getCountDelete() {
		return countDelete;
	}
	
	public Long getCountList() {
		return countList;
	}
	
	public Long getCountAutoComplete() {
		return countAutoComplete;
	}
	
	public Long getCountOp() {
		return countOp;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTenant(TenantLookupResult tenant) {
		this.tenant = tenant;
	}
	
	public void setYearOp(Long yearOp) {
		this.yearOp = yearOp;
	}
	
	public void setMonthOp(Long monthOp) {
		this.monthOp = monthOp;
	}
	
	public void setDayOp(Long dayOp) {
		this.dayOp = dayOp;
	}
	
	public void setHourOp(Long hourOp) {
		this.hourOp = hourOp;
	}
	
	public void setCountGet(Long countGet) {
		this.countGet = countGet;
	}
	
	public void setCountPost(Long countPost) {
		this.countPost = countPost;
	}
	
	public void setCountPut(Long countPut) {
		this.countPut = countPut;
	}
	
	public void setCountDelete(Long countDelete) {
		this.countDelete = countDelete;
	}
	
	public void setCountList(Long countList) {
		this.countList = countList;
	}
	
	public void setCountAutoComplete(Long countAutoComplete) {
		this.countAutoComplete = countAutoComplete;
	}
	
	public void setCountOp(Long countOp) {
		this.countOp = countOp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantOpCount other = (TenantOpCount) obj;
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
