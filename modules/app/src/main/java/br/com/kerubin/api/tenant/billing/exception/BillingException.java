package br.com.kerubin.api.tenant.billing.exception;

public class BillingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BillingException(String message) {
        super(message);
    }
	
	public BillingException(String message, Throwable cause) {
        super(message, cause);
    }

}
