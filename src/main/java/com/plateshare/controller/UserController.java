package com.plateshare.controller;

import com.plateshare.model.User;
import com.plateshare.service.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // Registration
    @PostMapping("/addUser")
    public String addUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam long phno,
            @RequestParam String address,
            @RequestParam String password,
            @RequestParam String role
    ) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhno(phno);
        user.setAddress(address);
        user.setPassword(password);
        user.setRole(role);

        userRepo.save(user);
        return "login";
    }

    // Login
    @PostMapping("/loginProcess")
    public String loginProcess(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {

        User user = userRepo.findByEmailAndPassword(email, password);

        if (user != null) {
            session.setAttribute("user", user);

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "adminProfile";
            } else if (user.getRole().equalsIgnoreCase("DONOR")) {
                return "donorProfile";
            } else {
                return "ngoProfile";
            }
        }

        return "login";
    }

    // Logout
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
    
}

