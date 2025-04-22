package com.pqwz.pqwzep.model;

import jakarta.persistence.*;
import lombok.*;

@Entity  // 🔹 Marks this class as a JPA entity (maps to a table in the DB)
@Table(name = "leave_balances") // 🔹 Specifies the table name in the DB
@Data   // 🔹 Lombok annotation: generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // 🔹 Lombok: generates a no-args constructor
@AllArgsConstructor // 🔹 Lombok: generates a constructor with all fields
@Builder            // 🔹 Lombok: enables the builder pattern for object creation
public class LeaveBalance {

    @Id // 🔹 Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔹 Auto-increment the ID in DB
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 🔹 Many leave balances can belong to one employee
    @JoinColumn(name = "employee_id", nullable = false) // 🔹 Maps to the foreign key column (employee_id)
    private Employee employee;

    @Enumerated(EnumType.STRING) // 🔹 Store the enum value as a string ("CASUAL", "SICK")
    @Column(name = "leave_type", nullable = false) // 🔹 Maps to the leave_type column
    private LeaveType leaveType;

    @Column(name = "remaining_days", nullable = false) // 🔹 Maps to the remaining_days column
    private int remainingDays;
}







