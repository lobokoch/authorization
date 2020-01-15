/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;


import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import br.com.kerubin.api.security.authorization.OrderStatus;

public class CreditOrderAdminListFilter {

	private java.util.UUID id;
	
	private java.util.List<String> orderUserName;
	
	private java.util.List<String> orderTenantName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate orderDateFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate orderDateTo;
	
	private java.math.BigDecimal orderValueFrom;
	
	private java.math.BigDecimal orderValueTo;
	
	private OrderStatus orderStatus;
	
	// Map field for developer customizing parameters.
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.UUID getId() {
		return id;
	}
			
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public java.util.List<String> getOrderUserName() {
		return orderUserName;
	}
	
	public void setOrderUserName(java.util.List<String> orderUserName) {
		this.orderUserName = orderUserName;
	}
	
	public java.util.List<String> getOrderTenantName() {
		return orderTenantName;
	}
	
	public void setOrderTenantName(java.util.List<String> orderTenantName) {
		this.orderTenantName = orderTenantName;
	}
	
	public java.time.LocalDate getOrderDateFrom() {
		return orderDateFrom;
	}
	
	public void setOrderDateFrom(java.time.LocalDate orderDateFrom) {
		this.orderDateFrom = orderDateFrom;
	}
	
	public java.time.LocalDate getOrderDateTo() {
		return orderDateTo;
	}
	
	public void setOrderDateTo(java.time.LocalDate orderDateTo) {
		this.orderDateTo = orderDateTo;
	}
	
	public java.math.BigDecimal getOrderValueFrom() {
		return orderValueFrom;
	}
	
	public void setOrderValueFrom(java.math.BigDecimal orderValueFrom) {
		this.orderValueFrom = orderValueFrom;
	}
	
	public java.math.BigDecimal getOrderValueTo() {
		return orderValueTo;
	}
	
	public void setOrderValueTo(java.math.BigDecimal orderValueTo) {
		this.orderValueTo = orderValueTo;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
			
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
