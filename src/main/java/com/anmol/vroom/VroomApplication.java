package com.anmol.vroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        exclude = UserDetailsServiceAutoConfiguration.class
)
public class VroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(VroomApplication.class, args);
    }
}