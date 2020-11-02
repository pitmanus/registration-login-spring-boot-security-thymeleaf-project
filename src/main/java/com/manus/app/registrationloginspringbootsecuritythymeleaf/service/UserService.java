package com.manus.app.registrationloginspringbootsecuritythymeleaf.service;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto.UserDTO;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;

public interface UserService {

    User save(UserDTO userDTO);
}
