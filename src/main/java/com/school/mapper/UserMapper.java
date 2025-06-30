package com.school.mapper;

import org.mapstruct.Mapper;

import com.school.bo.UserBO;
import com.school.dto.RegisterDTO;
import com.school.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	// DTO → BO
	UserBO toBO(RegisterDTO dto);

	// Entity → BO
	UserBO toBO(User entity);

	// BO → Entity
	User toEntity(UserBO bo);

	// Entity → DTO 
	RegisterDTO toDTO(User entity);
}
