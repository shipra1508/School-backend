package com.school.entity;

import com.school.role.Role;

import jakarta.persistence.*;

	@Entity
	@Table(name = "users")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 50)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;

	   	// Constructors
	    public User() {
	    }

	    public User(String username, String password, Role role) {
	        this.username = username;
	        this.password = password;
	        this.role = role;

	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
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

	    public Role getRole() {
	        return role;
	    }

	    public void setRole(Role role) {
	        this.role = role;
	    }
	}
