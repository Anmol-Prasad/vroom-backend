package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.response.RatingResponseDto;

import java.util.List;

public interface RatingService {
    void rateRide(Long rideId, int score, String review);
    List<RatingResponseDto> getRatingsForUser(Long userId);
}
