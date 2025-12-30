package com.anmol.vroom.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RatingResponseDto {

    private Long rideId;
    private Integer score;
    private String review;
    private Long raterId;
    private Long rateeId;
}
