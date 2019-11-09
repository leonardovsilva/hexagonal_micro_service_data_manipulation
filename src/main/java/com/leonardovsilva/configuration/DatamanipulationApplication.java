package com.leonardovsilva.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(ExternalApiProperty.class)
@ComponentScan(basePackages= {"com.leonardovsilva.adapter", "com.leonardovsilva.service"})
public class DatamanipulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatamanipulationApplication.class, args);
	}

}
