package com.pqwz.pqwzep.repository;

import com.pqwz.pqwzep.model.Referral;
import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.model.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {

    // Find all referrals by an employee
    List<Referral> findByEmployee(Employee employee);

    // Check for duplicate referral
    Optional<Referral> findByEmployeeAndJobOpeningAndCandidateName(Employee employee, JobOpening jobOpening, String candidateName);
}
