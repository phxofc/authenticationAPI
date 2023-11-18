package br.com.pedro.AuthenticationAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

}
