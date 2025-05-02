package com.pqwz.pqwzep.service;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.JobOpening;
import com.pqwz.pqwzep.model.Referral;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.repository.JobOpeningRepository;
import com.pqwz.pqwzep.repository.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReferralService {

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    public List<Referral> getReferralsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return referralRepository.findByEmployee(employee);
    }

    public boolean isDuplicateReferral(Long employeeId, Long jobId, String candidateName) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        JobOpening job = jobOpeningRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job opening not found"));
        return referralRepository.findByEmployeeAndJobOpeningAndCandidateName(employee, job, candidateName).isPresent();
    }

    public Referral createReferral(Long employeeId, Long jobId, String candidateName, String resumePath, String comments) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        JobOpening job = jobOpeningRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job opening not found"));

        Referral referral = Referral.builder()
                .employee(employee)
                .jobOpening(job)
                .candidateName(candidateName)
                .resumePath(resumePath)
                .comments(comments)
                .status("PENDING")
                .referredAt(LocalDateTime.now())
                .build();

        return referralRepository.save(referral);
    }
}

