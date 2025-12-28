package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.request.RideRequestDto;
import com.anmol.vroom.api.dto.response.RideHistoryResponseDto;
import com.anmol.vroom.api.dto.response.RideResponseDto;
import com.anmol.vroom.domain.entity.Ride;
import com.anmol.vroom.domain.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    @PostMapping("/request")
    public RideResponseDto requestRide(@RequestBody RideRequestDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long riderId = Long.valueOf(authentication.getName());

        Ride ride = rideService.requestRide(riderId, request);

        return new RideResponseDto(
                ride.getId(),
                ride.getStatus(),
                ride.getFare(),
                ride.getRequestedAt()
        );
    }

    @PostMapping("/{id}/accept")
    public RideResponseDto acceptRide(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long driverId = Long.valueOf(authentication.getName());

        Ride ride = rideService.accceptRide(id, driverId);

        return new RideResponseDto(ride.getId(), ride.getStatus(), ride.getFare(), ride.getRequestedAt());

    }

    @PostMapping("/{id}/start")
    public RideResponseDto startRide(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long driverId = Long.valueOf(authentication.getName());

        Ride ride = rideService.startRide(id, driverId);

        return new RideResponseDto(ride.getId(), ride.getStatus(), ride.getFare(), ride.getRequestedAt());
    }

    @PostMapping("/{id}/complete")
    public RideResponseDto completeRide(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // getName returns how we populated Authentication
        // We did new UsernamePasswordAuthenticationToken(userId.toString(),.....) while creating authentication object
        // Currently we only stored Id so this works if we stored a complex object, then getPrincipal would be needed
        Long driverId = Long.valueOf(authentication.getName());

        Ride ride = rideService.completeRide(id, driverId);

        return new RideResponseDto(ride.getId(), ride.getStatus(), ride.getFare(), ride.getRequestedAt());
    }



//    @PostMapping("/request")
//    public ResponseEntity<Void> requestRide() {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Void> getRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/{id}/cancel")
//    public ResponseEntity<Void> cancelRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/estimate")
//    public ResponseEntity<Void> estimateRide() {
//        return ResponseEntity.ok().build();
//    }
}
