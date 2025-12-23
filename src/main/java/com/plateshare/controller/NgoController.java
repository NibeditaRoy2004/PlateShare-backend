package com.plateshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.plateshare.model.Donation;
import com.plateshare.service.DonationRepository;

@RestController
@RequestMapping("/api/ngo")
@CrossOrigin(origins = "*")
public class NgoController {

    @Autowired
    private DonationRepository donationRepo;

    // Fetch pending donations for NGO
    @GetMapping("/donations")
    public List<Donation> getPendingDonations() {
        return donationRepo.findByStatus("PENDING");
    }

    // Accept donation
    @PutMapping("/accept/{id}")
    public Donation acceptDonation(@PathVariable int id) {

        Donation donation = donationRepo.findById(id).orElse(null);

        if (donation != null) {
            donation.setStatus("ACCEPTED");
            return donationRepo.save(donation);
        }

        return null;
    }
}
