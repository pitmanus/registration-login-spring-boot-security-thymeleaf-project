package com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto;


import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    @NotNull
    @Size(min=2, max = 30, message = "Length must be from 2 to 30 letters")
    private String firstName;
    @NotNull
    @Size(min=2, max = 30, message = "Length must be from 2 to 30 letters")
    private String lastName;
    @NotNull
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
    private String email;

    @NotNull
    @Pattern(regexp="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$", message="Password should contain at least one number, one letter, and be between 6-15 characters in length!")
    private String password;

    private List<Role> roles = new ArrayList<>();


    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
