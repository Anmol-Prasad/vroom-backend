package com.anmol.vroom;

import org.springframework.boot.SpringApplication;

public class TestVroomApplication {

	public static void main(String[] args) {
		SpringApplication.from(VroomApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
