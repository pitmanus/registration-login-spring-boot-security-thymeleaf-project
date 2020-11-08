package com.manus.app.registrationloginspringbootsecuritythymeleaf;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.service.UserService;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationLoginSpringBootSecurityThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginSpringBootSecurityThymeleafApplication.class, args);


	}

}
