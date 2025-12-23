package com.plateshare.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plateshare.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
    
    long countByRole(String role);
    List<User> findByRole(String role);

}
