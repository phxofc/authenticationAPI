package br.com.pedro.AuthenticationAPI.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;
import br.com.pedro.AuthenticationAPI.entities.User;

public interface UserDetailsRepository extends JpaRepository<User, Long > {
	UserDetails findByUsername(String username);
	
}
