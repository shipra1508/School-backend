package com.school.service;

import com.school.dto.TeacherDTO;
import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO getTeacherById(int id);
    TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO);
    void deleteTeacher(int id);

}