package com.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.User;
import com.school.role.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndRole(String username, Role role);

	Optional<User> findByUsername(String username);

}
