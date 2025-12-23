package com.plateshare.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plateshare.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
	
	List<Donation> findByDonorEmail(String donorEmail);
	
	List<Donation> findByStatus(String status);
}
