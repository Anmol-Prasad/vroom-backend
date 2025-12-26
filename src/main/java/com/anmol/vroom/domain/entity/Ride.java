package com.anmol.vroom.domain.entity;

import com.anmol.vroom.domain.enums.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@Table // We can also ignore this annotation. Spring automatically marks the entity as table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private User driver;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "pickup_lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "pickup_lng"))
    })
    private Location pickupLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "drop_lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "drop_lng"))
    })
    private Location dropLocation;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private Double fare;

    private LocalDateTime requestedAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
