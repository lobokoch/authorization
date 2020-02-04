package br.com.kerubin.api.security.authorization.event;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;
import br.com.kerubin.api.messaging.core.DomainEventPublisher;
import br.com.kerubin.api.messaging.core.DomainMessage;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationConstants;

@Component
public class ServiceDomainEventAdapterImpl implements ServiceDomainEventAdapter {
	
	@Inject
	private DomainEventPublisher publisher;
	
	@Override
	public DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String primitive) {
		
		return buildEnvelope(payload, 
				SecurityAuthorizationConstants.DOMAIN, 
				SecurityAuthorizationConstants.SERVICE,
				ServiceContext.getTenant(),
				ServiceContext.getUser(),
				primitive);
		
	}
	
	@Override
	public DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String tenant, String username, String primitive) {
		
		return buildEnvelope(payload, 
				SecurityAuthorizationConstants.DOMAIN, 
				SecurityAuthorizationConstants.SERVICE,
				tenant,
				username,
				primitive);
		
	}
	
	@Override
	public DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String domain, String service, 
			String tenant, String username, String primitive) {
		
		DomainEventEnvelope<DomainEvent> envelope = DomainEventEnvelopeBuilder
				.getBuilder(primitive, payload)
				.domain(domain)
				.service(service)
				.tenant(tenant)
				.user(username)
				.build();
		
		return envelope;
		
	}
	
	@Override
	public void publish(DomainEventEnvelope<DomainEvent> envelope) {
		publisher.publish(envelope);
	}
	
	@Override
	public void publish(DomainEventEnvelope<DomainEvent> envelope, String routing) {
		publisher.publish(envelope, routing);
	}
	
	@Override
	public void publish(DomainEventEnvelope<?> envelope,  String routingKey, String topicName) {
		publisher.publish(topicName, routingKey, envelope);
	}

	@Override
	public void publish(DomainMessage message) {
		publisher.publish(message);		
	}


	
	

}
