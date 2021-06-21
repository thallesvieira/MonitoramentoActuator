package com.agenda.agenda.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda.security.model.Login;
import com.agenda.agenda.security.model.Token;
import com.agenda.agenda.security.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Login login){
		String token = authenticationService.authenticateLogin(login);
		
		return ResponseEntity.ok(new Token(token));
	}
}
