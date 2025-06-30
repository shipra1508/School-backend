package com.school.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class TeacherDTO {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String subject;
	
	// Constructor
	public TeacherDTO() {

	}

	public TeacherDTO(int id, String name, String subject) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;	
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


}
