/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserLookupResult;
import br.com.kerubin.api.security.authorization.PaymentMethod;
import br.com.kerubin.api.security.authorization.OrderStatus;
import javax.validation.constraints.Future;

public class CreditOrder {

	private java.util.UUID id;
	
	@NotBlank(message="\"Usuário solicitante\" é obrigatório.")
	@Size(max = 255, message = "\"Usuário solicitante\" pode ter no máximo 255 caracteres.")
	private String orderUserName;
	
	@NotBlank(message="\"Tenant do usuário\" é obrigatório.")
	@Size(max = 255, message = "\"Tenant do usuário\" pode ter no máximo 255 caracteres.")
	private String orderTenantName;
	
	@NotNull(message="\"Usuário que realizou o pedido\" é obrigatório.")
	private SysUserLookupResult orderUser;
	
	@NotNull(message="\"Data do pedido\" é obrigatório.")
	private java.time.LocalDate orderDate;
	
	@NotNull(message="\"Valor do pedido\" é obrigatório.")
	private java.math.BigDecimal orderValue;
	
	@NotNull(message="\"Valor Bônus\" é obrigatório.")
	private java.math.BigDecimal orderBonusValue;
	
	@NotNull(message="\"Total créditos\" é obrigatório.")
	private java.math.BigDecimal orderTotalCredits;
	
	@NotNull(message="\"paymentMethod\" é obrigatório.")
	private PaymentMethod paymentMethod;
	
	@Size(max = 255, message = "\"Complemento\" pode ter no máximo 255 caracteres.")
	private String paymentMethodDescription;
	
	@NotNull(message="\"orderStatus\" é obrigatório.")
	private OrderStatus orderStatus;
	
	@Future(message="\"Data pagamento\" não pode ser futura.")
	private java.time.LocalDate orderPaidDate;
	
	private java.time.LocalDate orderCanceledDate;
	
	@Size(max = 255, message = "\"Histórico\" pode ter no máximo 255 caracteres.")
	private String orderHistory;
	
	@Size(max = 255, message = "\"Criado por\" pode ter no máximo 255 caracteres.")
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	@Size(max = 255, message = "\"Alterado por\" pode ter no máximo 255 caracteres.")
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public CreditOrder() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getOrderUserName() {
		return orderUserName;
	}
	
	public String getOrderTenantName() {
		return orderTenantName;
	}
	
	public SysUserLookupResult getOrderUser() {
		return orderUser;
	}
	
	public java.time.LocalDate getOrderDate() {
		return orderDate;
	}
	
	public java.math.BigDecimal getOrderValue() {
		return orderValue;
	}
	
	public java.math.BigDecimal getOrderBonusValue() {
		return orderBonusValue;
	}
	
	public java.math.BigDecimal getOrderTotalCredits() {
		return orderTotalCredits;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	public String getPaymentMethodDescription() {
		return paymentMethodDescription;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public java.time.LocalDate getOrderPaidDate() {
		return orderPaidDate;
	}
	
	public java.time.LocalDate getOrderCanceledDate() {
		return orderCanceledDate;
	}
	
	public String getOrderHistory() {
		return orderHistory;
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
	
	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}
	
	public void setOrderTenantName(String orderTenantName) {
		this.orderTenantName = orderTenantName;
	}
	
	public void setOrderUser(SysUserLookupResult orderUser) {
		this.orderUser = orderUser;
	}
	
	public void setOrderDate(java.time.LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public void setOrderValue(java.math.BigDecimal orderValue) {
		this.orderValue = orderValue;
	}
	
	public void setOrderBonusValue(java.math.BigDecimal orderBonusValue) {
		this.orderBonusValue = orderBonusValue;
	}
	
	public void setOrderTotalCredits(java.math.BigDecimal orderTotalCredits) {
		this.orderTotalCredits = orderTotalCredits;
	}
	
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public void setPaymentMethodDescription(String paymentMethodDescription) {
		this.paymentMethodDescription = paymentMethodDescription;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public void setOrderPaidDate(java.time.LocalDate orderPaidDate) {
		this.orderPaidDate = orderPaidDate;
	}
	
	public void setOrderCanceledDate(java.time.LocalDate orderCanceledDate) {
		this.orderCanceledDate = orderCanceledDate;
	}
	
	public void setOrderHistory(String orderHistory) {
		this.orderHistory = orderHistory;
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
		CreditOrder other = (CreditOrder) obj;
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
