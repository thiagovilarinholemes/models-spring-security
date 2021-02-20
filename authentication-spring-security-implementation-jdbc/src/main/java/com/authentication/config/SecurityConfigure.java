package com.authentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource; // Base de dados padrão do Spring Security
	
	/* Metódo de gerenciamento de autenticação por usuário no banco de dados */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource); // Aponta para a base de dados
//			.usersByUsernameQuery("SELECT username, password, enabled " /* Outra forma de consulta de usuário */
//					+ "FROM users "
//					+ "WHERE username = ?")
//			.authoritiesByUsernameQuery("SELECT username, authority"
//					+ "FROM authorities "
//					+ "WHERE username = ?");			
	}
	
	/* Metódo que está contida em WebSecurityConfigurerAdapter para configurar o acesso dos usuários  */
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
