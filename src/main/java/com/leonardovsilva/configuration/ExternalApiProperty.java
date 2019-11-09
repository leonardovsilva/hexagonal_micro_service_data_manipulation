package com.leonardovsilva.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app")
@Data
public class ExternalApiProperty {

	private String link;
}
