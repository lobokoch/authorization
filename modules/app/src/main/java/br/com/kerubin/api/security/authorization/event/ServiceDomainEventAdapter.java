package br.com.kerubin.api.security.authorization.event;

import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainMessage;

public interface ServiceDomainEventAdapter {

	void publish(DomainEventEnvelope<DomainEvent> envelope);

	void publish(DomainEventEnvelope<DomainEvent> envelope, String routingSuffix);
	
	void publish(DomainEventEnvelope<?> envelope, String routingKey, String topicName);

	DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String primitive);

	DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String tenant, String username,
			String primitive);

	DomainEventEnvelope<DomainEvent> buildEnvelope(DomainEvent payload, String domain, String service, String tenant,
			String username, String primitive);
	
	void publish(DomainMessage message);

}
