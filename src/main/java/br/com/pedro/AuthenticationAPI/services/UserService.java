package br.com.pedro.AuthenticationAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pedro.AuthenticationAPI.dto.UserDTO;
import br.com.pedro.AuthenticationAPI.entities.User;
import br.com.pedro.AuthenticationAPI.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;
	
	public UserDTO createdUser (User user) {
		
		User existsUser = userRepository.findByUsername(user.getUsername());
		
		if (existsUser != null) {
		      throw new Error("User already exists!");
		    }
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		User createdUser = userRepository.save(user);
		
		UserDTO dto = new UserDTO(createdUser);
		
		return dto;
	}
	
	
	
	public List<UserDTO> findAll(){
		
		 List<User> result = userRepository.findAll();
		 
		 return result.stream().map(x -> new UserDTO(x)).toList();
			
		
	}
	
	public UserDTO findbyId(Long id) {
		
		User findbyIdUser = userRepository.findById(id).get();
		UserDTO dto = new UserDTO(findbyIdUser);
		
		return dto;
	}

}
