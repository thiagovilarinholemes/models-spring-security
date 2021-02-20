package com.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.authentication.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AuthenticationSpringSecurityImplementationJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationSpringSecurityImplementationJpaApplication.class, args);
	}

}
