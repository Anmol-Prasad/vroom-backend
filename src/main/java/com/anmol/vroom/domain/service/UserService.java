package com.anmol.vroom.domain.service;

import com.anmol.vroom.api.dto.request.UpdateProfileRequestDto;
import com.anmol.vroom.domain.entity.User;

public interface UserService {
    User getCurrentUser(Long userId);

    User updateProfile(Long userId, UpdateProfileRequestDto request);
}
