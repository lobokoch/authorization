package br.com.kerubin.api.customer.payment.service;

import br.com.kerubin.api.customer.payment.model.CreditOrder;

public interface PaymentService {

	String createCreditOrder(CreditOrder creditOrder);

}
