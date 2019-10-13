package br.com.kerubin.api.customer.payment.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.customer.payment.model.CreditOrder;
import br.com.kerubin.api.customer.payment.model.ResponseText;
import br.com.kerubin.api.customer.payment.service.PaymentService;

@RestController
@RequestMapping("/security/authorization/payment")
public class PaymentController {
	
	@Inject
	private PaymentService paymentService;
	
	@PostMapping("/createCreditOrder")
	public ResponseEntity<ResponseText> createCreditOrder(@Valid @RequestBody CreditOrder creditOrder) {
		ResponseText result = new ResponseText(paymentService.createCreditOrder(creditOrder));
		return ResponseEntity.ok(result);
	}

}
