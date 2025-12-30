package com.anmol.vroom.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RideEstimateResponseDto {
    private double estimatedFare;
    private double distanceKm;
}
