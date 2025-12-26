package com.anmol.vroom.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequestDto {
    public double pickupLat;
    public double pickupLong;

    public double dropLat;
    public double dropLong;
}
