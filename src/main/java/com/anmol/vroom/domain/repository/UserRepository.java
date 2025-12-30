package com.anmol.vroom.domain.repository;

import com.anmol.vroom.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long Id);

    public Optional<User> findByPhone(String phone);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(String phone);
}
