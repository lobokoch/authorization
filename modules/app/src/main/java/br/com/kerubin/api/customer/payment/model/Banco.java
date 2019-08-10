package br.com.kerubin.api.customer.payment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.servicecore.mail.MailUtils.*;

@Getter
@Setter
@ToString
@Builder
public class Banco {

	private String bancoCodigo;
	private String bancoNome;
	private String agenciaNumero;
	private String agenciaDigito;
	private String contaNumero;
	private String contaDigito;

	public String toHTML() {
		StringBuilder sb = new StringBuilder().append("Banco: ").append(toStrong(bancoCodigo)).append(" - ")
				.append(toStrong(bancoNome)).append(BR).append("Agência: ").append(toStrong(agenciaNumero));

		if (isNotEmpty(agenciaDigito)) {
			sb.append(" dígito: ").append(toStrong(agenciaDigito));
		}

		sb.append(BR);

		sb.append("Conta Correte: ").append(toStrong(contaNumero)).append(" dígito: ").append(toStrong(contaDigito))
				.append(BR);

		return sb.toString();
	}

}
