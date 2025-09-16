package com.school.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.bo.TeacherBO;
import com.school.dto.TeacherDTO;
import com.school.entity.Teacher;
import com.school.exception.TeacherNotFoundException;
import com.school.mapper.TeacherMapper;
import com.school.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        TeacherBO bo = teacherMapper.toBO(teacherDTO);
        Teacher entity = teacherMapper.toEntity(bo);
        Teacher saved = teacherRepository.save(entity);
        return teacherMapper.toDTO(saved);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getTeacherById(int id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        return teacherMapper.toDTO(teacher);
    }

    @Override
    public TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        TeacherBO bo = teacherMapper.toBO(teacherDTO);
        existing.setName(bo.getName());
        existing.setSubject(bo.getSubject());

        Teacher updated = teacherRepository.save(existing);
        return teacherMapper.toDTO(updated);
    }

    @Override
    public void deleteTeacher(int id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

}
