package com.kryptokrauts.aepps.sdk.showcase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kryptokrauts.aeternity.sdk.service.keypair.KeyPairServiceConfiguration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "serviceconfig")
@Data
public class ServiceConfig implements WebMvcConfigurer {
	KeyPairServiceConfiguration local = KeyPairServiceConfiguration.configure().compile();

	// disable CORS locally
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
	}
}
