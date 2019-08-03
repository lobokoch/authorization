package br.com.kerubin.api.tenant.billing.exception;

public class BillingEnoughCreditsException extends BillingException {

	private static final long serialVersionUID = 1L;

	public BillingEnoughCreditsException(String message) {
		super(message);
	}
	
	public BillingEnoughCreditsException() {
		super("Not enough credits for operation.");
	}
	
	public BillingEnoughCreditsException(String message, Throwable cause) {
        super(message, cause);
    }

}
