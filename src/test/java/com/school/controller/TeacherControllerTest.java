package com.school.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dto.StudentDTO;
import com.school.dto.TeacherDTO;
import com.school.service.StudentService;
import com.school.service.TeacherService;

// Simulates a logged-in teacher for testing secured endpoints
@WithMockUser(username = "Shipra", roles = {"TEACHER","ADMIN"})
@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeacherService teacherService;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔸 Teacher Endpoints

    @Test
    void shouldAddTeacherSuccessfully() throws Exception {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Ritika", "English");
        Mockito.when(teacherService.createTeacher(Mockito.any(TeacherDTO.class))).thenReturn(teacherDTO);

        mockMvc.perform(post("/teachers/add")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().string("Teacher 'Ritika' added successfully"));
    }

    @Test
    void shouldFetchAllTeachers() throws Exception {
        List<TeacherDTO> teachers = Arrays.asList(
            new TeacherDTO(1, "Ritika", "English"),
            new TeacherDTO(2, "Meera", "Science")
        );
        Mockito.when(teacherService.getAllTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/teachers/view/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].name").value("Ritika"))
            .andExpect(jsonPath("$[1].subject").value("Science"));
    }

    @Test
    void shouldGetTeacherById() throws Exception {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Ritika", "English");
        Mockito.when(teacherService.getTeacherById(1)).thenReturn(teacherDTO);

        mockMvc.perform(get("/teachers/view/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Ritika"))
            .andExpect(jsonPath("$.subject").value("English"));
    }

    @Test
    void shouldUpdateTeacherSuccessfully() throws Exception {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Ritika", "History");
        Mockito.when(teacherService.updateTeacher(Mockito.eq(1), Mockito.any(TeacherDTO.class))).thenReturn(teacherDTO);

        mockMvc.perform(put("/teachers/update/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacherDTO)))
            .andExpect(status().isOk())
            .andExpect(content().string("Teacher 'Ritika' updated successfully"));
    }

    @Test
    void shouldDeleteTeacherSuccessfully() throws Exception {
        Mockito.doNothing().when(teacherService).deleteTeacher(1);

        mockMvc.perform(delete("/teachers/delete/1")
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string("Teacher deleted successfully"));
    }

    // 🔹 Teacher Managing Students

    @Test
    void shouldAddStudentSuccessfullyByTeacher() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1, "Ravi", 'M', "Grade 9", 88);
        Mockito.when(studentService.createStudent(Mockito.any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(post("/teachers/students/add")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().string("Student 'Ravi' added successfully by teacher"));
    }

    @Test
    void shouldViewAllStudentsManagedByTeacher() throws Exception {
        List<StudentDTO> students = Arrays.asList(
            new StudentDTO(1, "Ravi", 'M', "Grade 9", 88),
            new StudentDTO(2, "Sneha", 'F', "Grade 10", 91)
        );
        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/teachers/students/view"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].name").value("Ravi"))
            .andExpect(jsonPath("$[1].studentClass").value("Grade 10"));
    }

    @Test
    void shouldViewStudentByIdManagedByTeacher() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1, "Ravi", 'M', "Grade 9", 88);
        Mockito.when(studentService.getStudentById(1)).thenReturn(studentDTO);

        mockMvc.perform(get("/teachers/students/view/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Ravi"))
            .andExpect(jsonPath("$.marks").value(88));
    }

    @Test
    void shouldUpdateStudentSuccessfullyByTeacher() throws Exception {
        StudentDTO studentDTO = new StudentDTO(1, "Ravi", 'M', "Grade 10", 90);
        Mockito.when(studentService.updateStudent(Mockito.eq(1), Mockito.any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(put("/teachers/students/update/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
            .andExpect(status().isOk())
            .andExpect(content().string("Student 'Ravi' updated successfully by teacher"));
    }

    @Test
    void shouldDeleteStudentSuccessfullyByTeacher() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudent(1);

        mockMvc.perform(delete("/teachers/students/delete/1")
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string("Student deleted successfully by teacher"));
    }
}
