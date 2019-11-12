package com.leonardovsilva.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(ExternalApiProperty.class)
@ComponentScan(basePackages= {"com.leonardovsilva.adapter", "com.leonardovsilva.service"})
@EnableDiscoveryClient
@EnableHystrix
public class DatamanipulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatamanipulationApplication.class, args);
	}

}
