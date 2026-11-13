package com.benjamin.JobApplicationTracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
