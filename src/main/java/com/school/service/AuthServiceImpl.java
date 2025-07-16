package com.school.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.school.bo.UserBO;
import com.school.dto.LoginDTO;
import com.school.dto.RegisterDTO;
import com.school.entity.User;
import com.school.mapper.UserMapper;
import com.school.repository.UserRepository;
@Service
public class AuthServiceImpl implements AuthService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterDTO register(RegisterDTO dto) {
        // Convert DTO to BO
        UserBO userBO = userMapper.toBO(dto);

        // Check if user with username and role exists
        Optional<User> existingUser = userRepository.findByUsernameAndRole(userBO.getUsername(), userBO.getRole());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with the same username and role already exists");
        }
        // Encode password
        userBO.setPassword(passwordEncoder.encode(userBO.getPassword()));

        // Map to entity
        User user = userMapper.toEntity(userBO);

        // Save and return DTO
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    
    @Override
    public LoginDTO login(LoginDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required for login.");
        }

        System.out.println("Login attempt → Username: " + dto.getUsername());

        // Find user by username only (ignore role in input)
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with the given username.");
        }

        User user = optionalUser.get();

        boolean passwordMatches = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        // Login successful - return actual role from DB
        return new LoginDTO(user.getUsername(), null, user.getRole());
    }





}



