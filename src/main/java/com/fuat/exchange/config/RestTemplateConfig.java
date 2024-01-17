package com.fuat.exchange.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fuat.exchange.exception.RestTemplateResponseErrorHandler;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RestTemplateConfig {


	private final RestTemplateBuilder restTemplateBuilder;

	@Bean
	public RestTemplate restTemplateBean() {
		return this.restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
	}
}