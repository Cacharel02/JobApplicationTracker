package com.benjamin.JobApplicationTracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entreprise")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;
}
