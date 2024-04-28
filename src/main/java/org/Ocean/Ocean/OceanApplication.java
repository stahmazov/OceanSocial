package org.Ocean.Ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OceanApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanApplication.class, args);
	}

}
