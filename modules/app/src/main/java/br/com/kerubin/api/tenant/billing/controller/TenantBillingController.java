package br.com.kerubin.api.tenant.billing.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.tenant.billing.model.TenantUser;
import br.com.kerubin.api.tenant.billing.service.TenantBillingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/billing/tenant")
public class TenantBillingController {
	
	@Inject
	private TenantBillingService tenantBillingService;
	
	@PostMapping("/computeTenantOperation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void computeTenantOperation(@RequestBody TenantUser tenantUser) {
		try {
			tenantBillingService.computeTenantOperation(tenantUser.getTenant(), tenantUser.getUser(), tenantUser.getRequestMethod(), tenantUser.getUri());
		} catch (Exception e) {
			log.error("Error at computeBillingForTenant for tenant and user: " + tenantUser, e);
		}
	}

}
