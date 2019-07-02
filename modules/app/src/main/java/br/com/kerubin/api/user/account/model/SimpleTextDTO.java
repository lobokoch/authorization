package br.com.kerubin.api.user.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleTextDTO {
	
	private String text;
	
	public SimpleTextDTO() {
		
	}
	
	public SimpleTextDTO(String text) {
		this.text = text;
	}
}
