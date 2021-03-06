package com.manus.app.registrationloginspringbootsecuritythymeleaf.repository;

import com.manus.app.registrationloginspringbootsecuritythymeleaf.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByLastName(@Param("lastName") String name);

}
