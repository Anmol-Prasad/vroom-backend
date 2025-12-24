package com.anmol.vroom.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<Void> getProfile(){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/location")
    public ResponseEntity<Void> updateLocation() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/wallet/add")
    public ResponseEntity<Void> addWalletBalance() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rides/history")
    public ResponseEntity<Void> rideHistory() {
        return ResponseEntity.ok().build();
    }
}
