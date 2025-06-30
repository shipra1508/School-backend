package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.Student;

public interface StudentRepository extends JpaRepository <Student, Integer>{
	
	// CREATE: Save a new student or update existing one
    // studentRepository.save(student);

    // READ: Find a student by ID
    // studentRepository.findById(id);

    // READ: Get all students
    // studentRepository.findAll();

    // UPDATE: save() works for both create and update
    // studentRepository.save(updatedStudent);

    // DELETE: Delete a student by ID
    // studentRepository.deleteById(id);

    // CUSTOM QUERY EXAMPLE: Find all students by gender
    // List<Student> findByGender(char gender);

}
