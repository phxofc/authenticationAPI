package br.com.pedro.AuthenticationAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.AuthenticationAPI.dto.UserDTO;
import br.com.pedro.AuthenticationAPI.entities.User;
import br.com.pedro.AuthenticationAPI.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserDTO createdUser (User user) {
		
		User existsUser = userRepository.findByUsername(user.getUsername());
		
		if (existsUser != null) {
		      throw new Error("User already exists!");
		    }
		
		User createdUser = userRepository.save(user);
		
		UserDTO dto = new UserDTO(createdUser);
		
		return dto;
	}

}
