package br.com.pedro.AuthenticationAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.pedro.AuthenticationAPI.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
