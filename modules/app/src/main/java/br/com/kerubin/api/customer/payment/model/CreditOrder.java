package br.com.kerubin.api.customer.payment.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.kerubin.api.security.authorization.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreditOrder {
	
	private UUID id;
	
	@NotNull(message="'Valor do pedido' é obrigatório.")
	private BigDecimal orderValue;
	
	@NotNull(message="O 'Método de pagamento' é obrigatório.")
	private PaymentMethod paymentMethod;
	
	@NotBlank(message="A 'Descrição do método de pagamento' é obrigatória.")
	private String paymentMethodDescription;
	
	private String userEmail;
	
	public CreditOrder() {
		// 
	}

}
