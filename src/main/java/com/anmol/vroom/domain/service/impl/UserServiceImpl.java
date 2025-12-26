package com.anmol.vroom.domain.service.impl;

import com.anmol.vroom.api.dto.request.UpdateProfileRequestDto;
import com.anmol.vroom.domain.entity.User;
import com.anmol.vroom.domain.repository.UserRepository;
import com.anmol.vroom.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public User updateProfile(Long userId, UpdateProfileRequestDto request) {
        // Find the user using userId
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User does not exist"));

        // Update the email if allowed
        // Check if the email is not null and user isn't updating the same email
        if(request.getEmail() != null && !request.getEmail().equals(user.getEmail())){
            // Also need to check if the new email doesn't exist already
            userRepository.findByEmail(request.getEmail()).ifPresent((existingUser)-> {
                throw new RuntimeException("Email already in use");
            });

            user.setEmail(request.getEmail());
        }

        // Update the phone if allowed
        if(request.getPhone() != null && !request.getPhone().equals(user.getPhone())){
            // Check whether phone number already exists
            userRepository.findByPhone(request.getPhone()).ifPresent((existingUser)-> {
                throw new RuntimeException("Phone number already in use");
            });

            user.setPhone(request.getPhone());
        }

        return userRepository.save(user);
    }
}
