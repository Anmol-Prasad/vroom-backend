package com.anmol.vroom.domain.service.impl;

import com.anmol.vroom.api.dto.response.AuthResponseDto;
import com.anmol.vroom.api.dto.request.LoginRequestDto;
import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.repository.UserRepository;
import com.anmol.vroom.domain.service.AuthService;
import com.anmol.vroom.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(LoginRequestDto request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Email not registered"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException(("Invalid Credentials"));
        }

        String token = jwtService.generateToken(user.getId(), user.getRole().name());

        return new AuthResponseDto(token);
    }
}
