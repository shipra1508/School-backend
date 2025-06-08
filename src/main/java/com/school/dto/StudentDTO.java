package com.school.dto;

//StudentDTO is used to transfer student data between the frontend (API) and backend layers
public class StudentDTO {
	
	private int id;
	private String name;
    private char gender;
    private String studentClass;
    private int marks;
    
    
    //  constructor
    
    public StudentDTO() {
    	
    }

    
    public StudentDTO(int id, String name, char gender, String studentClass, int marks) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.studentClass = studentClass;
		this.marks = marks;
	}


	//getter,setter
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id=id;
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
