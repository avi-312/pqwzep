package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.dto.EmployeeLeaveResponse;
import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.LeaveBalance;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.service.LeaveBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves/balances")
@RequiredArgsConstructor
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    // 🔹 Get all leave balances for an employee
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<EmployeeLeaveResponse>> getBalances(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveBalanceService.getBalances(employeeId));
    }

}
