package com.school.controller;

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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dto.StudentDTO;
import com.school.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private StudentService studentService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void addStudentSuccessfully() throws Exception {
		StudentDTO studentDTO = new StudentDTO(1, "Shipra", 'F', "Grade 10", 85);
		Mockito.when(studentService.createStudent(Mockito.any(StudentDTO.class))).thenReturn(studentDTO);

		mockMvc.perform(post("/students/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(studentDTO))).andExpect(status().isCreated())
				.andExpect(content().string("Shipra added successfully !"));
	}

	@Test
	void fetchAllStudents() throws Exception {
		List<StudentDTO> students = Arrays.asList(
				new StudentDTO(1, "Shipra", 'F', "Grade 10", 95),
				new StudentDTO(1, "Shree", 'F', "Grade 10", 85)

		);
		Mockito.when(studentService.getAllStudents()).thenReturn(students);

		mockMvc.perform(get("/students/all")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].name").value("Shipra")).andExpect(jsonPath("$[1].name").value("Shree"));
	}

	@Test
	void getStudentById() throws Exception {
		StudentDTO studentDTO = new StudentDTO(1, "Shipra", 'F', "Grade 10", 85);
		Mockito.when(studentService.getStudentById(1)).thenReturn(studentDTO);

		mockMvc.perform(get("/students/view/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Shipra")).andExpect(jsonPath("$.studentClass").value("Grade 10"));
	}

	@Test
	void updateStudentSuccessfully() throws Exception {
	    StudentDTO studentDTO = new StudentDTO(1, "Shipra", 'F', "Grade 11", 85);

	    Mockito.when(studentService.updateStudent(Mockito.eq(1), Mockito.any(StudentDTO.class)))
	           .thenReturn(studentDTO);

	    mockMvc.perform(put("/students/update/1")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(studentDTO)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.name").value("Shipra"))
	        .andExpect(jsonPath("$.studentClass").value("Grade 11"));
	}


	@Test
	void deleteStudentSuccessfully() throws Exception {
		Mockito.doNothing().when(studentService).deleteStudent(1);

		mockMvc.perform(delete("/students/delete/1")).andExpect(status().isOk())
				.andExpect(content().string("Student with ID 1 deleted successfully"));
	}
}
