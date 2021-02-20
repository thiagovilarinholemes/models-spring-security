 package com.authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication.config.MyUserDetails;
import com.authentication.entities.User;
import com.authentication.repositories.UserRepository;


/* Esta classe implementa o serviço que irá buscar os dados do usuário
 * no banco de dados e se existir passar para o UserDetails. 
 * O nome da classe deve ser diferente do implements e da função UserDetails */
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
	
		/* Pesquisa no banco de dados se existe um userName passado */
		Optional<User> user = userRepository.findByUserName(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userName));
		
		return user.map(MyUserDetails::new).get(); 
	}

}
