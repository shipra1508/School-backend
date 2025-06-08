package com.school.service;

import java.util.List;

import com.school.dto.StudentDTO;

    /*
	 StudentService defines all business operations related to student data.
	 Acts as a contract to be implemented by service layer logic.
	 */
	public interface StudentService {

	    // Save a new student and return the saved student DTO
	    StudentDTO createStudent(StudentDTO studentDTO);

	    // Retrieve all students as a list of DTOs
	    List<StudentDTO> getAllStudents();

	    // Get a specific student by ID
	    StudentDTO getStudentById(int id);

	    // Update existing student using ID and updated data
	    StudentDTO updateStudent(int id, StudentDTO studentDTO);

	    // Delete a student by ID
	    void deleteStudent(int id);
	}



