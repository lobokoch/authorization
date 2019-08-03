package br.com.kerubin.api.tenant.billing.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.tenant.billing.exception.BillingEnoughCreditsException;
import br.com.kerubin.api.tenant.billing.exception.BillingException;
import br.com.kerubin.api.tenant.billing.model.TenantUser;
import br.com.kerubin.api.tenant.billing.service.TenantBillingService;

@RestController
@RequestMapping("/billing/tenant")
public class TenantBillingController {
	
	@Inject
	private TenantBillingService tenantBillingService;
	
	@SuppressWarnings({ "rawtypes" })
	@PostMapping("/computeTenantOperation")
	public ResponseEntity computeTenantOperation(@RequestBody TenantUser tenantUser) {
		try {
			tenantBillingService.computeTenantOperation(tenantUser.getTenant(), tenantUser.getUser(), tenantUser.getRequestMethod(), tenantUser.getUri());
			//System.out.println("computeTenantOperation HttpStatus.OK");
			return new ResponseEntity(HttpStatus.OK);
		} catch (BillingEnoughCreditsException e) {
			return new ResponseEntity(HttpStatus.PAYMENT_REQUIRED);
		} catch (BillingException e) {
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
