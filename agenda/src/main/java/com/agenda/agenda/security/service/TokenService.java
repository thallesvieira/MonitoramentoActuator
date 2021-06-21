package com.agenda.agenda.security.service;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.agenda.agenda.resource.persistence.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${token.jwt.expiration}")
	private String expiration;
	
	@Value("${token.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		Date expirationDate = getExpirationTime();
		 
		return Jwts.builder()
				.setIssuer("Api AgendaOnline")
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	private Date getExpirationTime() {
		return new Date(new Date().getTime() + Long.parseLong(expiration));
	}
	
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (Strings.isEmpty(token) || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
	public boolean validToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

			return true;
		
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
