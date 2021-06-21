package com.agenda.agenda.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agenda.agenda.resource.persistence.entity.User;
import com.agenda.agenda.resource.persistence.repository.UserRepository;
import com.agenda.agenda.security.model.Login;


@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent())
			return user.get();
			
		throw new UsernameNotFoundException("Usuário inválido.");
	}
	
	private UsernamePasswordAuthenticationToken authenticateToken(Login login) {
		return new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
	}
	
	public Authentication authenticateUser(UsernamePasswordAuthenticationToken dataLogin) {
		return authManager.authenticate(dataLogin);
	}
	
	public String authenticateLogin(Login login) {
		UsernamePasswordAuthenticationToken dataLogin = authenticateToken(login);
		
		try {
			Authentication authentication = authenticateUser(dataLogin);
			String token = tokenService.generateToken(authentication);

			return token;
		}catch (Exception e) {
			throw new RuntimeException("Login ou senha inválido.");
		}
	}

}
