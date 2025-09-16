package com.school.service;

import java.util.List;

import com.school.dto.TeacherDTO;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO getTeacherById(int id);
    TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO);
    void deleteTeacher(int id);

}