package br.com.pedro.AuthenticationAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
	
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	 @PostMapping("/login")
	 
	    public ResponseEntity login(@RequestBody UserDTO userdto){
		 
		 try {
			 
			 var usernamePassword = new UsernamePasswordAuthenticationToken(
					 userdto.getUsername(),
					 userdto.getPassword());
		        var auth = this.authenticationManager.authenticate(usernamePassword);


		        return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			 
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USUARIO OU SENHA INCORRETO");
		}
		 
	       
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
