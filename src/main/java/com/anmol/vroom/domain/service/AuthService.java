package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.response.AuthResponseDto;
import com.anmol.vroom.api.dto.request.LoginRequestDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);
}
