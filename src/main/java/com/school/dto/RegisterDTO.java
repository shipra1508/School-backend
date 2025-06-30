package com.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.role.Role;

public class RegisterDTO {

	public RegisterDTO(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public RegisterDTO() {

	}

	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//Won't display password in JSON responses 
	private String password;
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
