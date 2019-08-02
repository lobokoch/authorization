package br.com.kerubin.api.tenant.billing.service;

public interface TenantBillingService {

	void computeTenantOperation(String tenant, String user, String requestMethod, String uri);

}
