package com.anmol.vroom.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Lazy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ride ride;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User rater;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User ratee;

    private Integer score;

    private String review;
}
