package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.request.RideRequestDto;
import com.anmol.vroom.domain.entity.Ride;

import java.util.List;

public interface RideService {
    Ride requestRide(Long riderId, RideRequestDto request);

    List<Ride> getAvailableRides();

    Ride accceptRide(Long rideId, Long driverId);

    Ride startRide(Long rideId, Long driverId);

    Ride completeRide(Long rideId, Long driverId);
}
