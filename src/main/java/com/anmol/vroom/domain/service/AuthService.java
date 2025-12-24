package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.AuthResponse;
import com.anmol.vroom.api.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
}
