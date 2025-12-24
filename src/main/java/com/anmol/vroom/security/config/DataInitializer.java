package com.anmol.vroom.security.config;


import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.enums.Role;
import com.anmol.vroom.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.existsByEmail("test@vroom.com")) {
            return;
        }

        User user = User.builder()
                .name("Test User")
                .email("test@vroom.com")
                .phone("9999999999")
                .password(passwordEncoder.encode("password123"))
                .role(Role.RIDER)
                .walletBalance(0.0)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }
}

