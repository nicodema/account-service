package com.devsu.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO {
	private Long id;
	private String nombre;
}
