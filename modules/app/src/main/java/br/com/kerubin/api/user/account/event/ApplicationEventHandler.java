package br.com.kerubin.api.user.account.event;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.kerubin.api.messaging.core.DomainMessage;
import br.com.kerubin.api.messaging.utils.DomainEventUtils;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationConstants;
import br.com.kerubin.api.security.authorization.event.ServiceDomainEventAdapter;
import br.com.kerubin.api.user.account.model.UserAccountConfirmedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationEventHandler {
	
	@Inject
	private ServiceDomainEventAdapter eventAdapter;
	
	public ApplicationEventHandler() {
		System.out.println("ApplicationEventHandler has been created.");
	}
	
	@TransactionalEventListener
	private void handleConfirmationUserAccountEvent(UserAccountConfirmedEvent event) {
		log.info("Publishing event ConfirmationUserAccountEvent for: {}", event);
		
		DomainMessage message = DomainMessage.builder()
				.domain(SecurityAuthorizationConstants.DOMAIN)
				.service(SecurityAuthorizationConstants.SERVICE)
				.tenant(event.getTenant())
				.tenantAccountType(event.getTenantAccountType())
				.user(event.getUsername())				
				.primitive(UserAccountConfirmedEvent.USER_ACCOUNT_CONFIRMED_EVENT)
				.payload(DomainEventUtils.dtoToBytes(event))
				.build();
		
		eventAdapter.publish(message);
		
	}

}
