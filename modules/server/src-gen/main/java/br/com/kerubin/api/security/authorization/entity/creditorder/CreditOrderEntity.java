/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Filter;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.security.authorization.PaymentMethod;
import br.com.kerubin.api.security.authorization.OrderStatus;
import javax.validation.constraints.Future;

@Entity
@Table(name = "credit_order")

@FilterDef(name = "creditOrder", parameters = {
	@ParamDef(name = "orderTenantName", type = "string")
})


@Filters( {
    @Filter(name = "creditOrder", condition = ":orderTenantName = order_tenant_name")
} )

public class CreditOrderEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Usuário solicitante\" é obrigatório.")
	@Size(max = 255, message = "\"Usuário solicitante\" pode ter no máximo 255 caracteres.")
	@Column(name="order_user_name")
	private String orderUserName;
	
	@NotBlank(message="\"Tenant do usuário\" é obrigatório.")
	@Size(max = 255, message = "\"Tenant do usuário\" pode ter no máximo 255 caracteres.")
	@Column(name="order_tenant_name")
	private String orderTenantName;
	
	@NotNull(message="\"Usuário que realizou o pedido\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_user")
	private SysUserEntity orderUser;
	
	@NotNull(message="\"Data do pedido\" é obrigatório.")
	@Column(name="order_date")
	private java.time.LocalDate orderDate;
	
	@NotNull(message="\"Valor do pedido\" é obrigatório.")
	@Column(name="order_value")
	private java.math.BigDecimal orderValue;
	
	@NotNull(message="\"Valor Bônus\" é obrigatório.")
	@Column(name="order_bonus_value")
	private java.math.BigDecimal orderBonusValue;
	
	@NotNull(message="\"Total créditos\" é obrigatório.")
	@Column(name="order_total_credits")
	private java.math.BigDecimal orderTotalCredits;
	
	@NotNull(message="\"paymentMethod\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method")
	private PaymentMethod paymentMethod;
	
	@Size(max = 255, message = "\"Complemento\" pode ter no máximo 255 caracteres.")
	@Column(name="payment_method_description")
	private String paymentMethodDescription;
	
	@NotNull(message="\"orderStatus\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="order_status")
	private OrderStatus orderStatus;
	
	@Future(message="\"Data pagamento\" não pode ser futura.")
	@Column(name="order_paid_date")
	private java.time.LocalDate orderPaidDate;
	
	@Column(name="order_canceled_date")
	private java.time.LocalDate orderCanceledDate;
	
	@Size(max = 255, message = "\"Histórico\" pode ter no máximo 255 caracteres.")
	@Column(name="order_history")
	private String orderHistory;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getOrderUserName() {
		return orderUserName;
	}
	
	public String getOrderTenantName() {
		return orderTenantName;
	}
	
	public SysUserEntity getOrderUser() {
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
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName != null ? orderUserName.trim() : orderUserName; // Chamadas REST fazem trim.
	}
	
	public void setOrderTenantName(String orderTenantName) {
		this.orderTenantName = orderTenantName != null ? orderTenantName.trim() : orderTenantName; // Chamadas REST fazem trim.
	}
	
	public void setOrderUser(SysUserEntity orderUser) {
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
		this.paymentMethodDescription = paymentMethodDescription != null ? paymentMethodDescription.trim() : paymentMethodDescription; // Chamadas REST fazem trim.
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
		this.orderHistory = orderHistory != null ? orderHistory.trim() : orderHistory; // Chamadas REST fazem trim.
	}
	
	public void assign(CreditOrderEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setOrderUserName(source.getOrderUserName());
			this.setOrderTenantName(source.getOrderTenantName());
			this.setOrderUser(source.getOrderUser());
			this.setOrderDate(source.getOrderDate());
			this.setOrderValue(source.getOrderValue());
			this.setOrderBonusValue(source.getOrderBonusValue());
			this.setOrderTotalCredits(source.getOrderTotalCredits());
			this.setPaymentMethod(source.getPaymentMethod());
			this.setPaymentMethodDescription(source.getPaymentMethodDescription());
			this.setOrderStatus(source.getOrderStatus());
			this.setOrderPaidDate(source.getOrderPaidDate());
			this.setOrderCanceledDate(source.getOrderCanceledDate());
			this.setOrderHistory(source.getOrderHistory());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
		}
	}
	
	public CreditOrderEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public CreditOrderEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (CreditOrderEntity) visited.get(this);
		}
				
		CreditOrderEntity theClone = new CreditOrderEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setOrderUserName(this.getOrderUserName());
		theClone.setOrderTenantName(this.getOrderTenantName());
		theClone.setOrderUser(this.getOrderUser() != null ? this.getOrderUser().clone(visited) : null);
		theClone.setOrderDate(this.getOrderDate());
		theClone.setOrderValue(this.getOrderValue());
		theClone.setOrderBonusValue(this.getOrderBonusValue());
		theClone.setOrderTotalCredits(this.getOrderTotalCredits());
		theClone.setPaymentMethod(this.getPaymentMethod());
		theClone.setPaymentMethodDescription(this.getPaymentMethodDescription());
		theClone.setOrderStatus(this.getOrderStatus());
		theClone.setOrderPaidDate(this.getOrderPaidDate());
		theClone.setOrderCanceledDate(this.getOrderCanceledDate());
		theClone.setOrderHistory(this.getOrderHistory());
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
		CreditOrderEntity other = (CreditOrderEntity) obj;
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