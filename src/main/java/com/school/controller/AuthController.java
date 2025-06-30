package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.bo.UserBO;
import com.school.dto.RegisterDTO;
import com.school.entity.User;
import com.school.mapper.UserMapper;
import com.school.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authservice;
	
	@Autowired
    private UserMapper userMapper;
	
	@PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO dto) {
        UserBO bo = userMapper.toBO(dto);
        User savedUser = authservice.register(bo);
        RegisterDTO response = userMapper.toDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
