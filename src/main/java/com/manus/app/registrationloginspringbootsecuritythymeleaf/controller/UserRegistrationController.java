package com.manus.app.registrationloginspringbootsecuritythymeleaf.controller;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto.UserDTO;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserDTO userDTO){
        userService.save(userDTO);
        return "redirect:/registration?success";
    }

}
