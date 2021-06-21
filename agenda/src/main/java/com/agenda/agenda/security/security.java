package com.agenda.agenda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.agenda.agenda.resource.persistence.repository.UserRepository;
import com.agenda.agenda.security.filter.AuthenticationFilter;
import com.agenda.agenda.security.service.AuthenticationService;
import com.agenda.agenda.security.service.TokenService;


@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configura autenticação (login)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configurações de recursos estáticos(js, css, imagens, etc..)
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	//Configurações de autorizações
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().disable();
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		.antMatchers(HttpMethod.POST, "/users/").permitAll()
		.antMatchers(HttpMethod.GET, "/main/").permitAll()
		//.antMatchers(HttpMethod.GET, "/actuator/*").permitAll()
		.antMatchers("/v2/api-docs/**").permitAll().and()
		.authorizeRequests().antMatchers("/swagger-ui.html").permitAll().and()			
		.authorizeRequests().antMatchers("/configuration/**").permitAll().and()
		.authorizeRequests().antMatchers("/webjars/**").permitAll().and()			
		.authorizeRequests().antMatchers("/swagger-resources/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AuthenticationFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);


		//http.headers().frameOptions().disable();
		
    }

}
