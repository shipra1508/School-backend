package com.school.mapper;

import org.mapstruct.Mapper;

import com.school.bo.TeacherBO;
import com.school.dto.TeacherDTO;
import com.school.entity.Teacher;

/**
 * Converts data between Teacher Entity, BO, and DTO layers.
 */
@Mapper(componentModel = "spring") // Makes it injectable as a Spring bean
public interface TeacherMapper {

    // Converts DTO to BO 
    TeacherBO toBO(TeacherDTO dto);

    // Converts BO to DTO 
    TeacherDTO toDTO(TeacherBO bo);

    // Converts Entity (from DB) to BO 
    TeacherBO toBO(Teacher entity);

    // Converts BO to Entity 
    Teacher toEntity(TeacherBO bo);

    // Converts Entity directly to DTO 
    TeacherDTO toDTO(Teacher entity);
}
