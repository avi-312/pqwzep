package com.pqwz.pqwzep.dto;

import com.pqwz.pqwzep.model.LeaveStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LeaveHistoryResponse {
    private Long employeeId;
    private String leaveType;
    private int numberOfDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reportingManager;
    private String comments;
    private LeaveStatus status;
    private LocalDate appliedOn;
}

