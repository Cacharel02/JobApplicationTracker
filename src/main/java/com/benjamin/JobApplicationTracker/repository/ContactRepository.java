package com.benjamin.JobApplicationTracker.repository;

import com.benjamin.JobApplicationTracker.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("""
            SELECT c FROM Contact c
            JOIN FETCH c.company co
            WHERE co.user.id = :userId
            ORDER BY co.name ASC, c.name ASC
            """)
    List<Contact> findAllByOwner(@Param("userId") Long userId);

    @Query("""
            SELECT c FROM Contact c
            JOIN FETCH c.company co
            WHERE c.id = :contactId AND co.user.id = :userId
            """)
    Optional<Contact> findByIdAndOwner(@Param("contactId") Long contactId, @Param("userId") Long userId);

}
