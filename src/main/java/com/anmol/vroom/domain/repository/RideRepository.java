package com.anmol.vroom.domain.repository;

import com.anmol.vroom.domain.entity.Ride;
import com.anmol.vroom.domain.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    public List<Ride> findRidesByStatusAndDriverIsNull(RideStatus status);

    public List<Ride> findRidesByRiderIdOrDriverIdOrderByRequestedAtDesc(Long riderId, Long driverId);
}
