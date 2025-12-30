package com.anmol.vroom.domain.service.impl;

import com.anmol.vroom.api.dto.request.RideRequestDto;
import com.anmol.vroom.api.dto.response.RideEstimateResponseDto;
import com.anmol.vroom.domain.entity.Location;
import com.anmol.vroom.domain.entity.Ride;
import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.enums.RideStatus;
import com.anmol.vroom.domain.repository.RideRepository;
import com.anmol.vroom.domain.repository.UserRepository;
import com.anmol.vroom.domain.service.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    public final RideRepository rideRepository;
    public final UserRepository userRepository;

    @Override
    public Ride requestRide(Long riderId, RideRequestDto request) {
        User user = userRepository.findById(riderId).orElseThrow(()-> new RuntimeException("Rider does not exist"));

        Location pickupLocation = new Location(request.getPickupLat(), request.getPickupLong());
        Location dropLocation = new Location(request.getDropLong(), request.getDropLong());

        double fare = calculateFare(
                request.getPickupLat(),
                request.getPickupLong(),
                request.getDropLat(),
                request.getDropLong()
        );


        Ride ride = Ride.builder()
                .rider(user)
                .driver(null)
                .pickupLocation(pickupLocation)
                .dropLocation(dropLocation)
                .fare(fare)
                .status(RideStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .build();

        return rideRepository.save(ride);
    }

    @Override
    public List<Ride> getAvailableRides() {
        return rideRepository.findRidesByStatusAndDriverIsNull(RideStatus.REQUESTED);
    }

    @Override
    @Transactional
    public Ride accceptRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus()!= RideStatus.REQUESTED || ride.getDriver()!=null){
            throw new RuntimeException("Ride not available");
        }

        User driver =  userRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Driver not found"));

        ride.setDriver(driver);
        ride.setStatus(RideStatus.ACCEPTED);

        return ride;
    }

    @Override
    @Transactional
    public Ride startRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus() != RideStatus.ACCEPTED){
            throw new RuntimeException("Ride cannot be started");
        }

        if(!ride.getDriver().getId().equals(driverId)){
            throw new RuntimeException("Only assigned driver can start the ride");
        }

        ride.setStatus(RideStatus.STARTED);
        ride.setStartTime(LocalDateTime.now());

        return ride;
    }

    @Override
    @Transactional
    public Ride completeRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus() != RideStatus.STARTED){
            throw new RuntimeException("Ride cannot be completed");
        }

        if(!ride.getDriver().getId().equals(driverId)){
            throw new RuntimeException("Only the assigned driver can complete the ride");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.setEndTime(LocalDateTime.now());

        return ride;
    }

    @Override
    public List<Ride> getRideHistory(Long userId) {
        // First we passed riderId then we passed driverId
        return rideRepository.findRidesByRiderIdOrDriverIdOrderByRequestedAtDesc(userId, userId);
    }

    @Override
    public Ride getRideById(Long rideId, Long userId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new IllegalArgumentException("Ride not found"));

        boolean isRider = ride.getRider().getId().equals(userId);
        boolean isDriver = ride.getDriver().getId() != null && ride.getDriver().getId().equals(userId);

        if(!isRider && !isDriver){
            throw new SecurityException("You are not allowed to view this ride");
        }

        return ride;
    }

    @Override
    @Transactional
    public Ride cancelRide(Long rideId, Long userId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new IllegalArgumentException("Ride not found"));

        boolean isRider = ride.getRider().getId().equals(userId);
        boolean isDriver = ride.getDriver() != null && ride.getDriver().getId().equals(userId);

        if (!isRider && !isDriver) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "You are not allowed to cancel this ride"
            );
        }

        if (ride.getStatus() == RideStatus.STARTED || ride.getStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("Ride cannot be cancelled now");
        }

        // Driver can cancel only if ACCEPTED
        if (isDriver && ride.getStatus() != RideStatus.ACCEPTED) {
            throw new IllegalStateException("Driver cannot cancel at this stage");
        }

        ride.setStatus(RideStatus.CANCELLED);
        return ride;
    }

    @Override
    public RideEstimateResponseDto estimateFare(double pickupLat, double pickupLong, double dropLat, double dropLong) {
        double distanceKm = calculateDistanceKm(
                pickupLat, pickupLong,
                dropLat, dropLong
        );

        double fare = calculateFare(
                pickupLat, pickupLong,
                dropLat, dropLong
        );

        return new RideEstimateResponseDto(fare, distanceKm);
    }


    // HELPER FOR HAVERSINE
    private double calculateFare(
            double pickupLat,
            double pickupLng,
            double dropLat,
            double dropLng
    ) {
        final double BASE_FARE = 50;
        final double PER_KM_RATE = 10;

        double distanceKm = calculateDistanceKm(
                pickupLat, pickupLng,
                dropLat, dropLng
        );

        return BASE_FARE + (distanceKm * PER_KM_RATE);
    }

    private double calculateDistanceKm(
            double lat1, double lon1,
            double lat2, double lon2
    ) {
        final int R = 6371; // Earth radius in KM

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
