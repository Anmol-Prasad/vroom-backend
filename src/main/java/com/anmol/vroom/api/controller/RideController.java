package com.anmol.vroom.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @PostMapping("/request")
    public ResponseEntity<Void> requestRide() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estimate")
    public ResponseEntity<Void> estimateRide() {
        return ResponseEntity.ok().build();
    }
}
