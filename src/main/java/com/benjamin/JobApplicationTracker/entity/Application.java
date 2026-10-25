package com.benjamin.JobApplicationTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "application")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ApplicationState state;

    @Column(name = "channel")
    private String channel;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "position_id", nullable = false, unique = true)
    private Position position;
}
