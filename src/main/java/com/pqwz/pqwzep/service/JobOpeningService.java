package com.pqwz.pqwzep.service;

import com.pqwz.pqwzep.model.JobOpening;
import com.pqwz.pqwzep.repository.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOpeningService {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    // Get all job openings
    public List<JobOpening> getAllJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    // Create a new job opening (useful for testing)
    public JobOpening createJobOpening(JobOpening jobOpening) {
        return jobOpeningRepository.save(jobOpening);
    }
}
