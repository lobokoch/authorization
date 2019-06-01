package br.com.kerubin.api.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println("kerubin-fe:" + encoder.encode("kerubin-fe"));
		//System.out.println("Angel!81:" + encoder.encode("Angel!81"));
		//System.out.println("admin:" + encoder.encode("123"));
		System.out.println("admin:" + encoder.encode("Kerubin_Anonymous@!1"));

	}

}
