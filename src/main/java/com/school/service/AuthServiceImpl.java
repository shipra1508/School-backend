package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.bo.UserBO;
import com.school.entity.User;
import com.school.mapper.UserMapper;
import com.school.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 UserMapper userMapper;
	 

	 public User register(UserBO userBO) {
		    if (userRepository.findByUsername(userBO.getUsername()).isPresent()) {
		        throw new RuntimeException("Username already exists");
		    }

		    String encodedPassword = passwordEncoder.encode(userBO.getPassword());
		    User userEntity = userMapper.toEntity(userBO);
		    userEntity.setPassword(encodedPassword);

		    return userRepository.save(userEntity);
		}

	

}
