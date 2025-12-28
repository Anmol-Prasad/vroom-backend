package com.anmol.vroom.domain.service;

import com.anmol.vroom.domain.entity.Rating;

import java.util.List;

public interface RatingService {
    void rateRide(Long rideId, int score, String review);
    List<Rating> getRatingsForUser(Long userId);
}
