package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.request.RegisterRequestDto;
import com.anmol.vroom.api.dto.response.AuthResponseDto;
import com.anmol.vroom.api.dto.request.LoginRequestDto;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);

    void register(RegisterRequestDto request);
}
