package com.pqwz.pqwzep.repository;

import com.pqwz.pqwzep.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {

    // Get all leave applications by employee ID
    List<LeaveApplication> findByEmployeeId(Long employeeId);

    // Optional: Get all applications by status, if needed later
    // List<LeaveApplication> findByStatus(String status);
}
