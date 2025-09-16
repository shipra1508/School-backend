package com.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.school.entity.User;
import com.school.repository.UserRepository;
import com.school.role.Role;

import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SchoolBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(SchoolBackend1Application.class, args);
    }

    @Bean
    public CommandLineRunner createDefaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String defaultUsername = "admin";
            String defaultPassword = "admin123";
            Role defaultRole = Role.ADMIN;

            boolean userExists = userRepository.findByUsername(defaultUsername).isPresent();

            if (!userExists) {
                User defaultUser = new User(
                    defaultUsername,
                    passwordEncoder.encode(defaultPassword),
                    defaultRole
                );
                userRepository.save(defaultUser);
                System.out.println("Default admin user created: " + defaultUsername);
            } else {
                System.out.println("Default user already exists: " + defaultUsername);
            }
        };
    }
}
