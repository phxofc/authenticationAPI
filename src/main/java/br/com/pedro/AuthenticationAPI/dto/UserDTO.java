package br.com.pedro.AuthenticationAPI.dto;

import br.com.pedro.AuthenticationAPI.entities.User;

public class UserDTO {

	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	
	public UserDTO() {
		
	}


	public UserDTO(User user) {

		id = user.getId();
		name = user.getName();
		username = user.getUsername();
		password = user.getPassword();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
	
}
