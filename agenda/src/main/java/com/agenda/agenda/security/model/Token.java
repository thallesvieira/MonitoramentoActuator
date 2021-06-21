package com.agenda.agenda.security.model;

import lombok.Data;

@Data
public class Token {
	
	public Token(String token) {
		this.token = token;
		this.tipo = "Bearer";
	}
	
	private String token;
	private String tipo;
}
