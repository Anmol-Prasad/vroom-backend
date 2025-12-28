package com.anmol.vroom.domain.repository;

import com.anmol.vroom.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByRideIdAndRaterId(Long rideId, Long raterId);

    List<Rating> findByRateeIdOrderByIdDesc(Long rateeId);
}
