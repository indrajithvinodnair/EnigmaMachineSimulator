package com.wehrmacht.enigma;

import com.wehrmacht.enigma.config.ApplicationConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfigurationProperties.class)
public class EnigmaEncoderDecoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmaEncoderDecoderApplication.class, args);
	}
}
