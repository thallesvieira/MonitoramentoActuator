package com.agenda.agenda.security.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agenda.agenda.resource.persistence.entity.User;
import com.agenda.agenda.resource.persistence.repository.UserRepository;
import com.agenda.agenda.security.service.TokenService;

public class AuthenticationFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AuthenticationFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.userRepository = repository;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    
		String token = tokenService.getToken(request);
		
        if (!Strings.isEmpty(token) && tokenService.validToken(token)) {
        	authenticateUser(token);
		}else {
			if (request.getRequestURI().contains("actuator")) {
				String basicToken = request.getHeader("Authorization");
				StringTokenizer st = new StringTokenizer(basicToken);
				String basic = st.nextToken();
				
				if (basic.equalsIgnoreCase("BASIC")) {
					String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
					authenticateBasicUser(credentials);
				}
			} 
		}
		
		filterChain.doFilter(request, response);
	}



	private void authenticateUser(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = userRepository.findById(idUser).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private void authenticateBasicUser(String credentials) {
//		String[] credenciais = credentials.split(":");
//		
//		User user = userRepository.findByUsername(credenciais[0]).get();
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
//		
//		if (Objects.nonNull(user) && user.getUsername().equals(Constantes.USERNAME_ACTUATOR) && credenciais[1].equals(Constantes.SENHA_ACTUATOR))
//			SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}
