package com.plateshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.plateshare.model.Donation;
import com.plateshare.service.DonationRepository;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
public class DonationController {

    @Autowired
    private DonationRepository donationRepo;

    // Save donation
    @PostMapping
    public Donation addDonation(@RequestBody Donation donation) {
        return donationRepo.save(donation);
    }

    // Get all donations (for Admin)
    @GetMapping
    public List<Donation> getAllDonations() {
        return donationRepo.findAll();
    }
    
 // Get donations for a specific donor
    @GetMapping("/donor")
    public List<Donation> getDonationsByDonor(@RequestParam String email) {
        return donationRepo.findByDonorEmail(email);
    }

}
