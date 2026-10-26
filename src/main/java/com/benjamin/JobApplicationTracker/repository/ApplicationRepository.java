package com.benjamin.JobApplicationTracker.repository;

import com.benjamin.JobApplicationTracker.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByPositionId(Long positionId);

    Optional<Application> findByPositionId(Long positionId);

    @Query("""
            SELECT a FROM Application a
            JOIN FETCH a.position p
            JOIN FETCH p.company c
            WHERE a.id = :applicationId AND c.user.id = :userId
            """)
    Optional<Application> findByIdAndOwner(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
}
