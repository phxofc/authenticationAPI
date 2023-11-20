package br.com.pedro.AuthenticationAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.AuthenticationAPI.dto.UserDTO;
import br.com.pedro.AuthenticationAPI.entities.User;
import br.com.pedro.AuthenticationAPI.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public UserDTO create(@RequestBody User user) {
		
		return userService.createdUser(user);
	}
	
	
	@GetMapping
	public List<UserDTO> findAll() {	
		return userService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO findbyId(@PathVariable Long id) {	
		return userService.findbyId(id);
	}


}
