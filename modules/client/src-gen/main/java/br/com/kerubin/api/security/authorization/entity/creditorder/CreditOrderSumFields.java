/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

public class CreditOrderSumFields {
	
	private java.math.BigDecimal sumOrderValue;
	
	private java.math.BigDecimal sumOrderBonusValue;
	
	private java.math.BigDecimal sumOrderTotalCredits;
	
	public CreditOrderSumFields() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public java.math.BigDecimal getSumOrderValue() {
		return sumOrderValue;
	}
	
	public java.math.BigDecimal getSumOrderBonusValue() {
		return sumOrderBonusValue;
	}
	
	public java.math.BigDecimal getSumOrderTotalCredits() {
		return sumOrderTotalCredits;
	}
	
	public void setSumOrderValue(java.math.BigDecimal sumOrderValue) {
		this.sumOrderValue = sumOrderValue;
	}
	
	public void setSumOrderBonusValue(java.math.BigDecimal sumOrderBonusValue) {
		this.sumOrderBonusValue = sumOrderBonusValue;
	}
	
	public void setSumOrderTotalCredits(java.math.BigDecimal sumOrderTotalCredits) {
		this.sumOrderTotalCredits = sumOrderTotalCredits;
	}

}
