package com.anmol.vroom.domain.entity;

import com.anmol.vroom.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "phone")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Double walletBalance = 0.0;

    @Embedded
    private Location currentLocation;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
