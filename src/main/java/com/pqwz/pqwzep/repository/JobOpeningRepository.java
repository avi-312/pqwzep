package com.pqwz.pqwzep.repository;

import com.pqwz.pqwzep.model.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
    // You can add custom query methods here if needed later
}
