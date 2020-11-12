package com.manus.app.registrationloginspringbootsecuritythymeleaf.controller;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto.UserDTO;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.service.UserServiceImp;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/edituser/{id}")
    public ModelAndView editUser(@PathVariable Long id){
        UserDTO userDTO = userServiceImp.getById(id);
        return new ModelAndView("edituser", "selectedUser", userDTO);
    }

    @PostMapping("/editusercredentials")
    public String editUserCredentials(@Valid @ModelAttribute("selectedUser") UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("BINDING RESULT ERROR");
            return "/edituser";
        }else {
            userServiceImp.update(userDTO);
            return "redirect:/users";
        }
    }

    @GetMapping("/finduserbyname")
    public ModelAndView findByName(@ModelAttribute UserDTO userDTO){
        List<UserDTO> users = userServiceImp.getBySurname(userDTO.getLastName());
        return new ModelAndView("users", "userList", users);
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@ModelAttribute UserDTO userDTO){
        userServiceImp.deleteUser(userDTO.getId());
        return "redirect:/users";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
