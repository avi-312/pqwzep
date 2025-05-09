package com.pqwz.pqwzep.repository;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.LeaveBalance;
import com.pqwz.pqwzep.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    // 🔹 Get all leave balances for a specific employee
    List<LeaveBalance> findByEmployee(Employee employee);

    // 🔹 Get a specific leave type balance for an employee (e.g., CASUAL)
    Optional<LeaveBalance> findByEmployeeAndLeaveType(Employee employee, LeaveType leaveType);
}
