package com.anmol.vroom.api.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequestDto {

    @NotNull(message = "Pickup latitude is required")
    @DecimalMin(value = "-90.0", message = "Pickup latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Pickup latitude must be <= 90")
    private Double pickupLat;

    @NotNull(message = "Pickup longitude is required")
    @DecimalMin(value = "-180.0", message = "Pickup longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Pickup longitude must be <= 180")
    private Double pickupLong;

    @NotNull(message = "Drop latitude is required")
    @DecimalMin(value = "-90.0", message = "Drop latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Drop latitude must be <= 90")
    private Double dropLat;

    @NotNull(message = "Drop longitude is required")
    @DecimalMin(value = "-180.0", message = "Drop longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Drop longitude must be <= 180")
    private Double dropLong;
}
