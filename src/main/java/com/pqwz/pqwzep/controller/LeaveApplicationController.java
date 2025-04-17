package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.dto.LeaveApplicationRequest;
import com.pqwz.pqwzep.dto.LeaveHistoryResponse;
import com.pqwz.pqwzep.model.LeaveApplication;
import com.pqwz.pqwzep.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveApplicationController {

    private final LeaveApplicationService leaveApplicationService;

    // 🔘 Submit Leave Application
    @PostMapping("/apply")
    public ResponseEntity<Map<String, String>> applyLeave(@RequestBody LeaveApplicationRequest request) {
        String result = leaveApplicationService.applyLeave(
                request.getEmployeeId(),
                request.getLeaveType(),
                request.getNumberOfDays(),
                request.getStartDate(),
                request.getComments()
        );

        if (result.contains("submitted")) {
            return ResponseEntity.status(201).body(Map.of("message", result));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", result));
        }
    }

    // 🔘 Get Leave History for Employee
    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<LeaveHistoryResponse>> getHistory(@PathVariable Long employeeId) {
        List<LeaveHistoryResponse> history = leaveApplicationService.getLeaveHistory(employeeId);
        return ResponseEntity.ok(history);
    }

}
