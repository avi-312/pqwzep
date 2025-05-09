package com.pqwz.pqwzep.service;

import com.pqwz.pqwzep.dto.LeaveHistoryResponse;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.LeaveApplication;
import com.pqwz.pqwzep.model.LeaveBalance;
import com.pqwz.pqwzep.model.LeaveStatus;
import com.pqwz.pqwzep.model.LeaveType;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.repository.LeaveApplicationRepository;
import com.pqwz.pqwzep.repository.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveApplicationService {

    private final LeaveApplicationRepository leaveApplicationRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;

    // ✅ Apply for a leave
    public String applyLeave(Long employeeId, LeaveType leaveType, int numberOfDays,
                             LocalDate startDate, String comments) {

        // Step 1: Check if employee exists
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            return "Employee not found";
        }

        Employee employee = optionalEmployee.get();

        // Step 2: Check if sufficient leave balance exists
        Optional<LeaveBalance> balanceOptional =
                leaveBalanceRepository.findByEmployeeAndLeaveType(employee, leaveType);

        if (balanceOptional.isEmpty() || balanceOptional.get().getRemainingDays() < numberOfDays) {
            return "Insufficient leave balance for " + leaveType;
        }

        // Step 3: Calculate end date
        LocalDate endDate = startDate.plusDays(numberOfDays - 1);

        // Step 4: Create leave application object
        LeaveApplication application = LeaveApplication.builder()
                .employee(employee)
                .leaveType(leaveType)
                .numberOfDays(numberOfDays)
                .startDate(startDate)
                .endDate(endDate)
                .reportingManager(employee.getManagerName()) // You can fetch this dynamically later
                .comments(comments)
                .status(LeaveStatus.PENDING)
                .appliedOn(LocalDate.now())
                .build();

        // Step 5: Save application
        leaveApplicationRepository.save(application);

        // (Optional) Step 6: You can deduct leave balance after manager approves

        return "Leave application submitted successfully";
    }

    // ✅ Get all leave applications of an employee
    public List<LeaveApplication> getLeaveApplicationsByEmployee(Long employeeId) {
        return leaveApplicationRepository.findByEmployeeId(employeeId);
    }

    public List<LeaveHistoryResponse> getLeaveHistory(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            // handle not found
            throw new RuntimeException("Employee not found");
        }

        List<LeaveApplication> applications = leaveApplicationRepository.findByEmployeeId(employeeId);
        List<LeaveHistoryResponse> result = new ArrayList<>();

        for (LeaveApplication app : applications) {
            result.add(
                    LeaveHistoryResponse.builder()
                            .employeeId(app.getEmployee().getId())
                            .leaveType(app.getLeaveType().toString())
                            .numberOfDays(app.getNumberOfDays())
                            .startDate(app.getStartDate())
                            .endDate(app.getEndDate())
                            .reportingManager(app.getReportingManager())
                            .comments(app.getComments())
                            .status(app.getStatus())
                            .appliedOn(app.getAppliedOn())
                            .build()
            );
        }

        return result;
    }


}
