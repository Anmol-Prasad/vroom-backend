package com.anmol.vroom.domain.service.impl;

import com.anmol.vroom.api.dto.request.RideRequestDto;
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

        double fare = 100.00; // TODO

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
}
