package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.dto.TeacherDTO;
import com.school.service.StudentService;
import com.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    // CRUD for Teacher

    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO created = teacherService.createTeacher(teacherDTO);
        return ResponseEntity.status(201).body("Teacher '" + created.getName() + "' added successfully");
    }

    @GetMapping("/view/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") int id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable("id") int id, @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updated = teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok("Teacher '" + updated.getName() + "' updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") int id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    // Teacher managing student details

    @PostMapping("/students/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO created = studentService.createStudent(studentDTO);
        return ResponseEntity.status(201).body("Student '" + created.getName() + "' added successfully by teacher");
    }

    @GetMapping("/students/view")
    public ResponseEntity<List<StudentDTO>> viewAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/view/{id}")
    public ResponseEntity<StudentDTO> viewStudentById(@PathVariable("id") int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/students/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updated = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok("Student '" + updated.getName() + "' updated successfully by teacher");
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully by teacher");
    }
}
