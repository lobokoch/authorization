package br.com.kerubin.api.customer.credit.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.customer.credit.model.CreditBalance;
import br.com.kerubin.api.customer.credit.service.CreditService;

@RestController
@RequestMapping("credit")
public class CreditController {
	
	@Inject
	private CreditService creditService;
	
	@GetMapping("/getCreditBalance")
	public ResponseEntity<CreditBalance> getCreditBalance() {
		CreditBalance result = new CreditBalance(creditService.getCreditBalance());
		return ResponseEntity.ok(result);
	}

}
