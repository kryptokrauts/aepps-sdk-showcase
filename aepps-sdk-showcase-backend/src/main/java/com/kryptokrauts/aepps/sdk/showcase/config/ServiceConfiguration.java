package com.kryptokrauts.aepps.sdk.showcase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kryptokrauts.aeternity.sdk.domain.secret.impl.BaseKeyPair;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "serviceconfig")
@Getter
@Setter
public class ServiceConfiguration implements WebMvcConfigurer {
//	private KeyPairServiceConfiguration local = KeyPairServiceConfiguration.configure().compile();

	@JsonProperty("Environment configuration")
	private EnvironmentConfiguration envConfig = new EnvironmentConfiguration();

	// disable CORS locally
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}

	@Getter
	@Setter
	public class EnvironmentConfiguration {

		@JsonProperty("Base URL")
		private String baseUrl;

		@JsonProperty("Compiler URL")
		private String compilerUrl;

		@JsonProperty("Base Keypair")
		private BaseKeyPair baseKeyPair;
	}

}
