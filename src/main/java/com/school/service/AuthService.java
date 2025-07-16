package com.school.service;

import com.school.dto.LoginDTO;
import com.school.dto.RegisterDTO;

public interface AuthService {

	RegisterDTO register(RegisterDTO dto);
	LoginDTO login(LoginDTO dto);

}
