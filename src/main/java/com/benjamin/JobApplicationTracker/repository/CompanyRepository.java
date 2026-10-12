package com.benjamin.JobApplicationTracker.repository;

import com.benjamin.JobApplicationTracker.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByUserId(Long userId);

    Optional<Company> findByNameAndUserId(String name, Long userId);

    boolean existsByNameAndUserId(String name, Long userId);

    Optional<Company> findByIdAndUserId(Long id, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);
}
