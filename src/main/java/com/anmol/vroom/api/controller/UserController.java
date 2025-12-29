package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.request.UpdateProfileRequestDto;
import com.anmol.vroom.api.dto.request.WalletAddRequestDto;
import com.anmol.vroom.api.dto.response.RideHistoryResponseDto;
import com.anmol.vroom.api.dto.response.UserResponseDto;
import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.service.RideService;
import com.anmol.vroom.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "User profile, wallet, and location APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RideService rideService;

    @GetMapping("/me")
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(authentication.getName());
        return userService.getCurrentUser(userId);
    }

    @PutMapping("/profile")
    public User updateProfile(@RequestBody UpdateProfileRequestDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.valueOf(authentication.getName());

        return userService.updateProfile(userId, request);
    }

    @PostMapping("/wallet/add")
    public UserResponseDto addWalletBalance(@RequestBody @Valid WalletAddRequestDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.valueOf(authentication.getName());

        Double amount = request.getAmount();

        User user = userService.addToWallet(userId, amount);

        return new UserResponseDto(userId, user.getName(), user.getEmail(), user.getPhone(), user.getWalletBalance());
    }

    @GetMapping("/rides/history")
    public List<RideHistoryResponseDto> getRideHistory(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.valueOf(authentication.getName());

        return rideService.getRideHistory(userId)
                .stream()
                .map((ride) ->
                        new RideHistoryResponseDto(
                                ride.getId(),
                                ride.getStatus().name(),
                                ride.getFare(),
                                ride.getRequestedAt(),
                                ride.getStartTime(),
                                ride.getEndTime()
                        ))
                .toList();
    }
//    @GetMapping("/me")
//    public ResponseEntity<Void> getProfile(){
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/profile")
//    public ResponseEntity<Void> updateProfile() {
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/location")
//    public ResponseEntity<Void> updateLocation() {
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/wallet/add")
//    public ResponseEntity<Void> addWalletBalance() {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/rides/history")
//    public ResponseEntity<Void> rideHistory() {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/test-protected")
//    public String testProtected() {
//        return "JWT is working";
//    }

}
