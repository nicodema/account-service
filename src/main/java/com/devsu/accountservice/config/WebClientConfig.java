package com.devsu.accountservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Getter;
import lombok.Setter;

@Configuration
public class WebClientConfig {

	@Value("${clientService.url}")
	private String clientServiceUrl;

	@Value("${clientService.clientes.endpoint}")
	@Getter
	@Setter
	private String clientesEndpoint;

	@Bean
	public WebClient webClient() {
		return WebClient
				.builder()
				.baseUrl(clientServiceUrl)
				.build();
	}

}
