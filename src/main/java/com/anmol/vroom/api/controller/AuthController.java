package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.request.RegisterRequestDto;
import com.anmol.vroom.api.dto.response.AuthResponseDto;
import com.anmol.vroom.api.dto.request.LoginRequestDto;
import com.anmol.vroom.domain.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Tag(name = "Auth", description = "Authentication APIs")
// Marks this class as a controller
// Handles all the HTTP requests and all the request converted from JSON to Java
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto request){
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto request){
        authService.register(request);
        return ResponseEntity.status(201).body(Map.of("message", "User registered successfully"));
    }

//    @PostMapping("/register")
//    public ResponseEntity<Void> register(){
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<Void> me(){
//        return ResponseEntity.ok().build();
//    }
}
