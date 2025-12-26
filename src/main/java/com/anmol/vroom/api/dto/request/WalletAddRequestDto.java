package com.anmol.vroom.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletAddRequestDto {
    @NotNull
    @Min(1)
    private Double amount;
}
