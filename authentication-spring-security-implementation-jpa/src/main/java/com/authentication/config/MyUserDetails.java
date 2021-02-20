package com.authentication.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authentication.entities.User;

/* Está classe irá passar para a classe UserDetailsService - MyUserDetailsService
 * os detalhes do usuário como: userName, password, expirado...
 * É uma espécie de model. */
public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private Boolean active;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.password= user.getPassword();
		this.active = user.isActive();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	public MyUserDetails() {
	}

	/* Passa a lista de permissão */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	/* Busca a senha */
	@Override
	public String getPassword() {

		return password;
	}

	/* Busca o userName */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	/* Envia se a senha não expirou */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* Envia se a conta não está bloqueada */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/* Envia se a credencial não está expirada */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* Envia se a conta está habilitada */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

}
