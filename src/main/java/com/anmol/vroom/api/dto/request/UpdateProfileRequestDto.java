package com.anmol.vroom.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDto {
    private String name;
    private String email;
    private String phone;
}
