package com.anmol.vroom.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter // Else Jackson serialisation wouldn't work
@NoArgsConstructor // Needed by Hibernate for instantiation
@AllArgsConstructor // Me
public class Location {
    private Double latitude;
    private Double longitude;
}
