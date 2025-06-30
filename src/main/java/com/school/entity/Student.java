package com.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity tells Spring that this class maps to a table in the database
@Entity
@Table(name = "Student")
public class Student {
	//@Id tells primary key of the table
	@Id
	
	//GeneratedValue tells to auto-generates  value using DB identity (auto-increment)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Name cannot be null
	@Column(nullable = false)
	private String name;
	
    private char gender;
    private String studentClass;
    private int marks;
    
    //constructors
    public Student(){
    	
    }
    
    public Student(int id,String name, char gender, String studentClass , int marks){
    	this.id=id;
    	this .name=name;
        this.gender=gender;
        this.studentClass=studentClass;
        this.marks=marks;
    }
    
    //getter,setters

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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}


    
	
    
	
}