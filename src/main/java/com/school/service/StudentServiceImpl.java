package com.school.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.StudentRepository.StudentRepository;
import com.school.bo.StudentBO;
import com.school.dto.StudentDTO;
import com.school.entity.Student;
import com.school.exception.StudentNotFoundException;
import com.school.mapper.StudentMapper;


@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentMapper studentMapper;
	
	//Save new student details
	 @Override
	    public StudentDTO createStudent(StudentDTO studentDTO) {
	        StudentBO bo = studentMapper.toBO(studentDTO); // DTO → BO
	        Student entity = studentMapper.toEntity(bo);   // BO → Entity
	        Student savedEntity = studentRepository.save(entity); // Save to DB
	        return studentMapper.toDTO(savedEntity); // Return as DTO
	 }
	 
	// Fetch all students
	    @Override
	    public List<StudentDTO> getAllStudents() {
	        return studentRepository.findAll().stream()
	                .map(studentMapper::toDTO) // Entity → DTO
	                .collect(Collectors.toList());
	    }

	    // Get a student by ID
	    @Override
	    public StudentDTO getStudentById(int id) {
	        Student student = studentRepository.findById(id)
	                .orElseThrow(() -> StudentNotFoundException.forId(id));
	        return studentMapper.toDTO(student);
	    }

	    

	    // Update a student
	    @Override
	    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
	        Student existing = studentRepository.findById(id)
	                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
	        
	        // Update entity fields manually or via BO mapping
	        StudentBO bo = studentMapper.toBO(studentDTO);
	        existing.setName(bo.getName());
	        existing.setGender(bo.getGender());
	        existing.setStudentClass(bo.getStudentClass());
	        existing.setMarks(bo.getMarks());

	        Student updated = studentRepository.save(existing);
	        return studentMapper.toDTO(updated);
	    }

	    // Delete a student by ID
	    @Override
	    public void deleteStudent(int id) {
	        if (!studentRepository.existsById(id)) {
	            throw new StudentNotFoundException("Cannot delete. Student not found with id: " + id);
	        }
	        studentRepository.deleteById(id);
	    }
	}

	
	

