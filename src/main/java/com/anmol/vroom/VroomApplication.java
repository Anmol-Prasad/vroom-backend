package com.anmol.vroom;

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void debugEnv() {
        System.out.println("DB_URL = " + System.getenv("DB_URL"));
        System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD = " + System.getenv("DB_PASSWORD"));
    }

}

