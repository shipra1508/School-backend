package com.school.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.bo.UserBO;
import com.school.dto.LoginDTO;
import com.school.dto.RegisterDTO;
import com.school.entity.User;
import com.school.exception.InvalidCredentialsException;
import com.school.mapper.UserMapper;
import com.school.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterDTO register(RegisterDTO dto) {
        UserBO userBO = userMapper.toBO(dto);

        Optional<User> existingUser = userRepository.findByUsernameAndRole(userBO.getUsername(), userBO.getRole());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with the same username and role already exists");
        }

        userBO.setPassword(passwordEncoder.encode(userBO.getPassword()));
        User user = userMapper.toEntity(userBO);
        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public LoginDTO login(LoginDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) {
            throw new InvalidCredentialsException("Username and password are required for login.");
        }

        System.out.println("Login attempt → Username: " + dto.getUsername());
        System.out.println("DTO → Password: " + dto.getPassword());

        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        System.out.println("User found in DB: " + optionalUser.isPresent());

        if (optionalUser.isEmpty()) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        User user = optionalUser.get();
        System.out.println("Encoded password in DB: " + user.getPassword());

        boolean passwordMatches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        System.out.println("Password match result: " + passwordMatches);

        if (!passwordMatches) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return new LoginDTO(user.getUsername(), null, user.getRole());
    }
}
