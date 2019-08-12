package br.com.kerubin.api.customer.payment.exception;

public class CustomerPaymentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomerPaymentException(String message) {
		super(message);
	}
	
	public CustomerPaymentException(String message, Throwable cause) {
		super(message, cause);
	}

}
