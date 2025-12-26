package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.response.AuthResponseDto;
import com.anmol.vroom.api.dto.request.LoginRequestDto;
import com.anmol.vroom.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
