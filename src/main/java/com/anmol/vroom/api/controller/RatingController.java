package com.anmol.vroom.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RatingController {

    @PostMapping("/rides/{id}/rate")
    public ResponseEntity<Void> rateRide(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}/ratings")
    public ResponseEntity<Void> getUserRatings(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
