package com.benjamin.JobApplicationTracker.repository;

import com.benjamin.JobApplicationTracker.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("""
       SELECT p FROM Position p
       JOIN FETCH p.company c
       WHERE c.user.id = :userId
       """)
    List<Position> findAllByOwner(@Param("userId") Long userId);

    @Query("""
            SELECT p FROM Position p
            JOIN p.company c
            WHERE p.id = :id AND c.user.id = :userId
            """
    )
    Optional<Position> findByIdAndCompanyUserId(Long id, Long userId);

}
