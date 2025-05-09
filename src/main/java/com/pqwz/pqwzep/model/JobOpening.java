package com.pqwz.pqwzep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "job_openings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String location;

    @Column(name = "experience_required")
    private String experienceRequired;

    @Column(name = "employment_type")
    private String employmentType;

    private String overview;

    @Column(name = "key_responsibilities", columnDefinition = "TEXT")
    private String keyResponsibilities;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

