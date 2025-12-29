package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.response.RideResponseDto;
import com.anmol.vroom.domain.service.RideService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Drivers", description = "Driver availability and ride handling")
@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final RideService rideService;

    @GetMapping("/rides/available")
    public List<RideResponseDto> getAvailableRides(){
        return rideService.getAvailableRides()
                .stream()
                .map(ride -> new RideResponseDto(
                        ride.getId(),
                        ride.getStatus(),
                        ride.getFare(),
                        ride.getRequestedAt()
                ))
                .toList();
    }

//    @GetMapping("/rides/available")
//    public ResponseEntity<Void> availableRides() {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/rides/{id}/accept")
//    public ResponseEntity<Void> acceptRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/rides/{id}/start")
//    public ResponseEntity<Void> startRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/rides/{id}/complete")
//    public ResponseEntity<Void> completeRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/status")
//    public ResponseEntity<Void> updateDriverStatus() {
//        return ResponseEntity.ok().build();
//    }
}
