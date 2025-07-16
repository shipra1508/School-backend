package com.school.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.school.bo.UserBO;
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
}



