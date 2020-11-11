package com.manus.app.registrationloginspringbootsecuritythymeleaf.controller;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto.UserDTO;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.service.UserServiceImp;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private UserServiceImp userServiceImp;

    public MainController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping({"/index", "/"})
    public String mainPage(){
        return "index";
    }


    @GetMapping("/users")
    public ModelAndView getAllUsers(){
        List<UserDTO> userDTOList = userServiceImp.getAllUsers();
        return new ModelAndView("users", "userList", userDTOList);
    }

    @GetMapping("/finduser")
    public ModelAndView getUserByName(){
        return new ModelAndView("finduser", "foundUser", new UserDTO());
    }

    @GetMapping("/finduserbyname")
    public ModelAndView findByName(@ModelAttribute UserDTO userDTO){
        List<UserDTO> users = userServiceImp.getBySurname(userDTO.getLastName());
        return new ModelAndView("users", "userList", users);
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@ModelAttribute UserDTO userDTO){
        userServiceImp.deleteUser(userDTO.getId());
        return "redirect:/index";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
