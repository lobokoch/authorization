package br.com.kerubin.api.customer.credit.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreditBalance {
	
	private BigDecimal balanceValue;
	
	public CreditBalance() {
		
	}
	
	public CreditBalance(BigDecimal balanceValue) {
		this();
		this.balanceValue = balanceValue;
	}

}
