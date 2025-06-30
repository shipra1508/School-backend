package com.school.service;

import com.school.bo.UserBO;
import com.school.entity.User;

public interface AuthService {

	User register(UserBO userBO);

}
