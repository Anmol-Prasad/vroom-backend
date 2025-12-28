package com.anmol.vroom.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RideHistoryResponseDto {
    private Long id;
    private String status;
    private Double fare;
    private LocalDateTime requestedAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
