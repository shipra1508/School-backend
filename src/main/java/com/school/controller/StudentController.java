package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dto.StudentDTO;
import com.school.service.StudentService;

@RestController
@RequestMapping("/students") // Class level mapping
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST: /students/add
    @PostMapping("/add") //Method level mapping
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO created = studentService.createStudent(studentDTO);
        String message = created.getName() + " added successfully !";
        return ResponseEntity.status(201).body(message);
    }

    // GET: /students/all
    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // GET: /students/view/{id}
    @GetMapping("/view/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") int id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // PUT: /students/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updated = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE: /students/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        String message = "Student with ID " + id + " deleted successfully";
        return ResponseEntity.ok(message);
    }

}
