package com.pqwz.pqwzep.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Foreign key to Employee
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // ✅ Leave Type (CASUAL / SICK)
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type", nullable = false)
    private LeaveType leaveType;

    @Column(name = "number_of_days", nullable = false)
    private int numberOfDays;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "reporting_manager", nullable = false)
    private String reportingManager;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    // 🔁 Status: PENDING by default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING;

    // 🕒 Applied Date
    @Column(name = "applied_on")
    private LocalDate appliedOn = LocalDate.now();
}
