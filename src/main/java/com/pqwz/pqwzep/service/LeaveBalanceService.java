package com.pqwz.pqwzep.service;

import com.pqwz.pqwzep.dto.EmployeeLeaveResponse;
import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.LeaveBalance;
import com.pqwz.pqwzep.model.LeaveType;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.repository.LeaveBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final EmployeeRepository employeeRepository;

    // ✅ Get leave balance for specific employee and leave type
    public Optional<LeaveBalance> getLeaveBalance(Employee employee, LeaveType leaveType) {
        return leaveBalanceRepository.findByEmployeeAndLeaveType(employee, leaveType);
    }

    // ✅ Check if balance is sufficient
    public boolean hasSufficientLeave(Employee employee, LeaveType leaveType, int requestedDays) {
        Optional<LeaveBalance> optional = getLeaveBalance(employee, leaveType);
        return optional.isPresent() && optional.get().getRemainingDays() >= requestedDays;
    }

    // 🔄 Optional: Deduct leave balance after application is approved
    public void deductLeave(Employee employee, LeaveType leaveType, int days) {
        Optional<LeaveBalance> optional = getLeaveBalance(employee, leaveType);
        if (optional.isPresent()) {
            LeaveBalance balance = optional.get();
            balance.setRemainingDays(balance.getRemainingDays() - days);
            leaveBalanceRepository.save(balance);
        }
    }

    // 🔍 Get all leave balances for an employee (for controller use)
    public List<LeaveBalance> getAllLeaveBalances(Employee employee) {
        return leaveBalanceRepository.findByEmployee(employee);
    }

    public List<EmployeeLeaveResponse> getBalances(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()) {
            // to do throw exception that employee does not exist
        }
        List <LeaveBalance> leaveBalance = leaveBalanceRepository.findByEmployee(employee.get());
        List<EmployeeLeaveResponse> res = new ArrayList<>();
        leaveBalance.forEach(leave -> {
            EmployeeLeaveResponse emp = EmployeeLeaveResponse.builder()
                    .employeeId(leave.getEmployee().getId())
                    .id(leave.getId())
                    .leaveType(leave.getLeaveType().toString())
                    .remainingDays(leave.getRemainingDays())
                    .build();
            res.add(emp);
        });
        return res;
    }
}
