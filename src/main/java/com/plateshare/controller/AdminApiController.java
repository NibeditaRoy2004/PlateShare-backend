package com.plateshare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateshare.model.User;
import com.plateshare.service.DonationRepository;
import com.plateshare.service.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminApiController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DonationRepository donationRepo;

    @GetMapping("/counts")
    public Map<String, Long> getCounts() {
        Map<String, Long> data = new HashMap<>();

        data.put("donors", userRepo.countByRole("DONOR"));
        data.put("ngos", userRepo.countByRole("NGO"));
        data.put("donations", donationRepo.count());

        return data;
    }

    @GetMapping("/ngos")
    public List<User> getNGOs() {
        return userRepo.findByRole("NGO");
    }
}
