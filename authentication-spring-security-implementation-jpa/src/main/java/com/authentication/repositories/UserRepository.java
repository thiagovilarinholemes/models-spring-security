package com.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authentication.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String userName);
}
