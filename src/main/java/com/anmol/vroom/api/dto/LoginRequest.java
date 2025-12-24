package com.anmol.vroom.api.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
