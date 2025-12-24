package com.anmol.vroom.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @GetMapping("/rides/available")
    public ResponseEntity<Void> availableRides() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rides/{id}/accept")
    public ResponseEntity<Void> acceptRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rides/{id}/start")
    public ResponseEntity<Void> startRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rides/{id}/complete")
    public ResponseEntity<Void> completeRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status")
    public ResponseEntity<Void> updateDriverStatus() {
        return ResponseEntity.ok().build();
    }
}
