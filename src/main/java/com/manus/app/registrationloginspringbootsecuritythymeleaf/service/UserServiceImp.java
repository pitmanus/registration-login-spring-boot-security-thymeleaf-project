package com.manus.app.registrationloginspringbootsecuritythymeleaf.service;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.dto.UserDTO;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.Role;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;
import com.manus.app.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }

    public User update(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                Arrays.asList(new Role(userDTO.getRoles().stream().findFirst().get().getName()))
        );
        return userRepository.save(user);
    }


    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().
                stream().map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(),user.getEmail()))
                .collect(Collectors.toList());
    }


    public List<UserDTO> getBySurname(String surName){
        return userRepository.findByLastName(surName)
        .stream().map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(),user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id){
        return userRepository.findById(id)
                .stream().map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(),user.getEmail(), user.getPassword(), user.getRoles()))
                .findAny()
                .orElse(null);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //Spring Security expecting authorities
    //that's why here we converting roles to authorities
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){

       return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }
}
