package com.anmol.vroom.api.controller;

import com.anmol.vroom.api.dto.request.RateRideRequestDto;
import com.anmol.vroom.api.dto.response.RatingResponseDto;
import com.anmol.vroom.domain.entity.Rating;
import com.anmol.vroom.domain.service.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Rating", description = "Rider and Driver rating APIs")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/rides/{id}/rate")
    public ResponseEntity<Void> rateRide(@PathVariable Long id, @RequestBody @Valid RateRideRequestDto request){
       ratingService.rateRide(id, request.getScore(), request.getReview());
       return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}/ratings")
    public ResponseEntity<List<RatingResponseDto>> getRatingsForUser(@PathVariable Long id){
        return ResponseEntity.ok(
                ratingService.getRatingsForUser(id)
        );
    }

//    @PostMapping("/rides/{id}/rate")
//    public ResponseEntity<Void> rateRide(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/users/{id}/ratings")
//    public ResponseEntity<Void> getUserRatings(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
}
