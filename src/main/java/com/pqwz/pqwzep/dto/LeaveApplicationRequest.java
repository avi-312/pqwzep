package com.pqwz.pqwzep.dto;

import com.pqwz.pqwzep.model.LeaveType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveApplicationRequest {
    private Long employeeId;
    private LeaveType leaveType;
    private int numberOfDays;
    private LocalDate startDate;
    private String comments;
}
