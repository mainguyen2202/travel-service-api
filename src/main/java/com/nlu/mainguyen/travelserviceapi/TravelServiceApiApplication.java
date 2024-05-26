package com.nlu.mainguyen.travelserviceapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TravelServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServiceApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// registry.addMapping("/health-check").allowedOrigins("http://localhost:9000");
				registry.addMapping("/**")
				.allowedMethods("POST","GET",  "PUT", "DELETE")
				.allowedOrigins("http://localhost:3000", "http://localhost:3006");
			}
		};
	}
}