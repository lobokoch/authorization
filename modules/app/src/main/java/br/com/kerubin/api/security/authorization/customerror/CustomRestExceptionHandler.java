package br.com.kerubin.api.security.authorization.customerror;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.kerubin.api.customer.payment.exception.CustomerPaymentException;
import br.com.kerubin.api.servicecore.error.ApiError;
import br.com.kerubin.api.servicecore.error.RestExceptionHandler;
import br.com.kerubin.api.user.account.exception.UserAccountException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends RestExceptionHandler {
	
	@ExceptionHandler(UserAccountException.class)
    protected ResponseEntity<Object> handleEntityNotFound(UserAccountException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
	
	@ExceptionHandler(CustomerPaymentException.class)
	protected ResponseEntity<Object> handleCustomerPaymentException(CustomerPaymentException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

}
