package com.school.mapper;

import org.mapstruct.Mapper;
import com.school.dto.StudentDTO;
import com.school.bo.StudentBO;
import com.school.entity.Student;
 //A Mapper automatically converts data between Entity, BO, and DTO layers, and with MapStruct, the mapping code is auto-generated at compile time based on method signatures.
// MapStruct auto-generates conversions, saving time and preventing errors.
 
@Mapper(componentModel = "spring") // Allows Spring to inject this mapper
public interface StudentMapper {

    // Converts API data (DTO) to BO for service layer
    //Manual equivalent:
      /* StudentBO bo = new StudentBO();
       bo.setId(dto.getId());
       bo.setName(dto.getName());
       bo.setGender(dto.getGender());
       bo.setStudentClass(dto.getStudentClass());
       bo.setMarks(dto.getMarks());*/
    StudentBO toBO(StudentDTO dto);

    // Converts BO to DTO for returning to frontend
    //Manual equivalent:
    /* StudentDTO dto = new StudentDTO();
       dto.setId(bo.getId());
       dto.setName(bo.getName());
       dto.setGender(bo.getGender());
       dto.setStudentClass(bo.getStudentClass());
       dto.setMarks(bo.getMarks());
       dto.setGrade(bo.getGrade());
       dto.setRemarks(bo.getRemarks())*/
    StudentDTO toDTO(StudentBO bo);

    // Converts DB entity to BO for use in business logic
    //Manual equivalent:
    /* StudentBO bo = new StudentBO();
     bo.setId(entity.getId());
     bo.setName(entity.getName());
     bo.setGender(entity.getGender());
     bo.setStudentClass(entity.getStudentClass());
     bo.setMarks(entity.getMarks());*/
    StudentBO toBO(Student entity);

    // Converts BO to Entity for saving to the database
    // Manual equivalent:
    /*Student entity = new Student();
     entity.setId(bo.getId());
     entity.setName(bo.getName());
     entity.setGender(bo.getGender());
     entity.setStudentClass(bo.getStudentClass());
     entity.setMarks(bo.getMarks());*/
     Student toEntity(StudentBO bo);
     
  // Converts Entity directly to DTO (for read operations)
     // Manual equivalent:
     /*
     StudentDTO dto = new StudentDTO();
     dto.setId(entity.getId());
     dto.setName(entity.getName());
     dto.setGender(entity.getGender());
     dto.setStudentClass(entity.getStudentClass());
     dto.setMarks(entity.getMarks());
     */
     StudentDTO toDTO(Student entity);
}