package com.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	/* Faz as configurações do usuário, autenticação */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	/* Faz as configurações da rota, autorização */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN") /* Permite acesso somente para o ADMIN */
			.antMatchers("/user").hasAnyRole("USER", "ADMIN") /* Permite acesso para USER e ADMIN, para adicionar mais de uma role utilize hasAnyRole("USER", "ADMIN") */
			.antMatchers("/**").permitAll() /* Permite acesso para todos os usuários, devemos inserir o CSS e o JS */ 
				.and().formLogin(); /* Autenticação fornecido pelo Spring Security por form */
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
