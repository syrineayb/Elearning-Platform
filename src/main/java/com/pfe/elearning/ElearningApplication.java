package com.pfe.elearning;

import com.pfe.elearning.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class ElearningApplication {

	public static void main(String[] args) {

		SpringApplication.run(ElearningApplication.class, args);
	}

}
