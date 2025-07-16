package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.LoginDTO;
import com.school.dto.RegisterDTO;
import com.school.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
		RegisterDTO registeredUser = authService.register(dto);
		String message = registeredUser.getUsername() + " of role " + registeredUser.getRole()
				+ " registered successfully!!";
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
		LoginDTO loginedUser = authService.login(dto);
		String message = loginedUser.getUsername() + " logged in successfully as "+ loginedUser.getRole() ;
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
	}

}
