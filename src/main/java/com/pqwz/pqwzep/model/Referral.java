package com.pqwz.pqwzep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "referrals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id", nullable = false)
    private JobOpening jobOpening;

    @Column(name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "resume_path", nullable = false)
    private String resumePath;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    @Column(name = "referred_at")
    private LocalDateTime referredAt = LocalDateTime.now();
}
