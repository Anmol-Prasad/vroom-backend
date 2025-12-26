package com.anmol.vroom.api.dto.response;

import com.anmol.vroom.domain.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RideResponseDto {

    private Long id;
    private RideStatus status;
    private Double fare;
    private LocalDateTime requestedAt;
}
