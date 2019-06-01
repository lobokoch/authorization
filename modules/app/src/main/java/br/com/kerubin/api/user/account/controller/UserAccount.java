package br.com.kerubin.api.user.account.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.kerubin.api.security.authorization.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UserAccount {
	
	@NotBlank(message="O \"Nome\" é obrigatório.")
	@Size(message="O nome deve ter de 5 até 250 caracteres", min = 5, max = 250)
	private String name;
	
	@NotBlank(message="O \"E-mail\" é obrigatório.")
	@Email(message = "E-mail inválido.")
	private String email;
	
	@NotBlank(message="A \"Senha\" é obrigatória.")
	@Size(message="A senha deve ter de 6 até 50 caracteres", min = 6, max = 50)
	private String password;
	
	@NotNull(message="O\"Tipo da Conta\" é obrigatório.")
	private AccountType accountType;
	
	public UserAccount() {
		
	}

}
