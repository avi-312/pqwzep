package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.model.JobOpening;
import com.pqwz.pqwzep.service.JobOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/openings")
@CrossOrigin(origins = "*")
public class JobOpeningController {

    @Autowired
    private JobOpeningService jobOpeningService;

    @GetMapping
    public List<JobOpening> getAllOpenings() {
        return jobOpeningService.getAllJobOpenings();
    }

    // Optional: Create opening (can be used for testing from Postman)
    @PostMapping
    public JobOpening createOpening(@RequestBody JobOpening jobOpening) {
        return jobOpeningService.createJobOpening(jobOpening);
    }
}
