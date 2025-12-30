package com.anmol.vroom.domain.service.impl;

import com.anmol.vroom.api.dto.response.RatingResponseDto;
import com.anmol.vroom.domain.entity.Rating;
import com.anmol.vroom.domain.entity.Ride;
import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.enums.RideStatus;
import com.anmol.vroom.domain.repository.RatingRepository;
import com.anmol.vroom.domain.repository.RideRepository;
import com.anmol.vroom.domain.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RideRepository rideRepository;
    private final RatingRepository ratingRepository;

    @Override
    public void rateRide(Long rideId, int score, String review) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = Long.valueOf(authentication.getName());

        Ride ride = rideRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride does not exist"));

        if(ride.getStatus() != RideStatus.COMPLETED){
            throw new RuntimeException("Ride not completed");
        }

        User rater;
        User ratee;

        if(ride.getRider().getId().equals(userId)){
            rater = ride.getRider();
            ratee = ride.getDriver();
        }
        else if(ride.getDriver().getId().equals(userId)){
            rater = ride.getDriver();
            ratee = ride.getRider();
        }
        else{
            throw new IllegalStateException("User not part of this ride");
        }

        if(ratingRepository.existsByRideIdAndRaterId(rideId, rater.getId())){
            throw new IllegalStateException("You have already rated this ride");
        }

        Rating rating = Rating.builder()
                .ride(ride)
                .rater(rater)
                .ratee(ratee)
                .score(score)
                .review(review)
                .build();

        ratingRepository.save(rating);
    }

    @Override
    public List<RatingResponseDto> getRatingsForUser(Long userId) {

        List<Rating> ratings = ratingRepository.findByRateeIdOrderByIdDesc(userId);

        return ratings.stream()
                .map(rating -> new RatingResponseDto(
                        rating.getRide().getId(),
                        rating.getScore(),
                        rating.getReview(),
                        rating.getRater().getId(),
                        rating.getRatee().getId()
                ))
                .toList();
    }

}
