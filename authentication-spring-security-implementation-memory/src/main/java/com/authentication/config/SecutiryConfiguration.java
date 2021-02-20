package com.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity /* Habilita a segurança na Web */
/* Classe de configuração do AuthenticaionManagerBuild */
public class SecutiryConfiguration extends WebSecurityConfigurerAdapter{
	
	/* Função que está contida em WebSecurityConfigurerAdapter para configurar o acesso dos usuários  */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* Insira as configurações para auth object */
		
		/* Autenticação em memória */ 
		auth.inMemoryAuthentication()
			.withUser("carina")
			.password("123")
			.roles("USER") 
			.and() /* Adiciona um novo usuário em memória */
			.withUser("thiago")
			.password("123")
			.roles("ADMIN");
	}
	
	/* Função que está contida em WebSecurityConfigurerAdapter para configurar o acesso as rotas */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN") /* Permite acesso somente para o ADMIN */
			.antMatchers("/user").hasAnyRole("USER", "ADMIN") /* Permite acesso para USER e ADMIN, para adicionar mais de uma role utilize hasAnyRole("USER", "ADMIN") */
			.antMatchers("/", "static/css", "static/js").permitAll() /* Permite acesso para todos os usuários, devemos inserir o CSS e o JS */ 
				.and().formLogin(); /* Tipo de autenticação */
	}
	
	/* Função para criptografar as senhas */ 
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
