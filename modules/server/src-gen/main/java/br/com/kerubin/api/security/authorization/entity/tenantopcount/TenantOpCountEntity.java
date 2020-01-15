/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tenant_op_count")
public class TenantOpCountEntity  {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Size(max = 255, message = "\"description\" pode ter no máximo 255 caracteres.")
	@Column(name="description")
	private String description;
	
	@NotNull(message="\"Tenant\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant")
	private TenantEntity tenant;
	
	@NotNull(message="\"Ano da operação\" é obrigatório.")
	@Column(name="year_op")
	private Long yearOp;
	
	@NotNull(message="\"Mês da operação\" é obrigatório.")
	@Column(name="month_op")
	private Long monthOp;
	
	@NotNull(message="\"Dia da operação\" é obrigatório.")
	@Column(name="day_op")
	private Long dayOp;
	
	@NotNull(message="\"Hora da operação\" é obrigatório.")
	@Column(name="hour_op")
	private Long hourOp;
	
	@NotNull(message="\"Quantidade de operações GET simples\" é obrigatório.")
	@Column(name="count_get")
	private Long countGet;
	
	@NotNull(message="\"Quantidade de operações POST\" é obrigatório.")
	@Column(name="count_post")
	private Long countPost;
	
	@NotNull(message="\"Quantidade de operações PUT\" é obrigatório.")
	@Column(name="count_put")
	private Long countPut;
	
	@NotNull(message="\"Quantidade de operações DELETE\" é obrigatório.")
	@Column(name="count_delete")
	private Long countDelete;
	
	@NotNull(message="\"Quantidade de operações LIST\" é obrigatório.")
	@Column(name="count_list")
	private Long countList;
	
	@NotNull(message="\"Quantidade de operações AUTO COMPLETE\" é obrigatório.")
	@Column(name="count_auto_complete")
	private Long countAutoComplete;
	
	@NotNull(message="\"Quantidade de outras operações\" é obrigatório.")
	@Column(name="count_op")
	private Long countOp;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TenantEntity getTenant() {
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
		this.description = description != null ? description.trim() : description; // Chamadas REST fazem trim.
	}
	
	public void setTenant(TenantEntity tenant) {
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
	
	public void assign(TenantOpCountEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setDescription(source.getDescription());
			this.setTenant(source.getTenant());
			this.setYearOp(source.getYearOp());
			this.setMonthOp(source.getMonthOp());
			this.setDayOp(source.getDayOp());
			this.setHourOp(source.getHourOp());
			this.setCountGet(source.getCountGet());
			this.setCountPost(source.getCountPost());
			this.setCountPut(source.getCountPut());
			this.setCountDelete(source.getCountDelete());
			this.setCountList(source.getCountList());
			this.setCountAutoComplete(source.getCountAutoComplete());
			this.setCountOp(source.getCountOp());
		}
	}
	
	public TenantOpCountEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public TenantOpCountEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (TenantOpCountEntity) visited.get(this);
		}
				
		TenantOpCountEntity theClone = new TenantOpCountEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setDescription(this.getDescription());
		theClone.setTenant(this.getTenant() != null ? this.getTenant().clone(visited) : null);
		theClone.setYearOp(this.getYearOp());
		theClone.setMonthOp(this.getMonthOp());
		theClone.setDayOp(this.getDayOp());
		theClone.setHourOp(this.getHourOp());
		theClone.setCountGet(this.getCountGet());
		theClone.setCountPost(this.getCountPost());
		theClone.setCountPut(this.getCountPut());
		theClone.setCountDelete(this.getCountDelete());
		theClone.setCountList(this.getCountList());
		theClone.setCountAutoComplete(this.getCountAutoComplete());
		theClone.setCountOp(this.getCountOp());
		
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
		TenantOpCountEntity other = (TenantOpCountEntity) obj;
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
