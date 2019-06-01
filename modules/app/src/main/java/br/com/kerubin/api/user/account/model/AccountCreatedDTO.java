package br.com.kerubin.api.user.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AccountCreatedDTO {
	
	@NonNull
	private String text;
	
	

}
