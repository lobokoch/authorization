package br.com.kerubin.api.customer.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseText {
	
	private String text;
	
	public ResponseText() {
		
	}
	
	public ResponseText(String text) {
		this();
		this.text = text;
	}

}
