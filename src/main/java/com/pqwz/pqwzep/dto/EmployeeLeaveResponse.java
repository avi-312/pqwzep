package com.pqwz.pqwzep.dto;


import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.LeaveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveResponse{
    private Long id;
    private Long employeeId;
    private String leaveType;
    private int remainingDays;

}
