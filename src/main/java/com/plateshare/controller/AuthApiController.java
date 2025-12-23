package com.plateshare.controller;

import com.plateshare.model.User;
import com.plateshare.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthApiController {

    @Autowired
    private UserRepository userRepo;

    // API: Register user (called from HTML/JS frontend)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userRepo.save(user);
        return ResponseEntity.ok("Registered successfully");
    }

    // API: Login user (called from HTML/JS frontend)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User dbUser = userRepo.findByEmailAndPassword(
                user.getEmail(), user.getPassword());

        if (dbUser == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // Return full user object as JSON (role will be used by frontend)
        return ResponseEntity.ok(dbUser);
    }
}
