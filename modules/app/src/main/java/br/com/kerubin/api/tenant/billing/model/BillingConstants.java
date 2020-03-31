package br.com.kerubin.api.tenant.billing.model;

import java.math.BigDecimal;

import javax.persistence.LockModeType;

public class BillingConstants {
	
	public static final BigDecimal OPERATION_COST = new BigDecimal("0.0050"); // meio centavo.
	
	public static final String LOCK_TIMEOUT = "javax.persistence.lock.timeout";
	public static final String LOCK_TIMEOUT_VALUE = "5000"; // 30 segundos
	
	public static final LockModeType PESSIMISTIC_WRITE = LockModeType.PESSIMISTIC_WRITE;

}
