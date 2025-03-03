package com.devsu.accountservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.devsu.accountservice.config.WebClientConfig;
import com.devsu.accountservice.dto.ClienteDTO;
import com.devsu.accountservice.exception.BadRequestException;
import com.devsu.accountservice.exception.ClienteNoEncontradoException;
import com.devsu.accountservice.exception.ConexionServicioClienteException;
import com.devsu.accountservice.model.Cuenta;
import com.devsu.accountservice.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private WebClientConfig webClientConfig;

	@Autowired
	private WebClient webClient;

	@Override
	public Optional<Cuenta> obtenerPorNumeroCuenta(String numeroCuenta) {
		return cuentaRepository.findByNumeroCuenta(numeroCuenta);
	}

	@Override
	public ClienteDTO obtenerClientePorId(Long clientId) {
		try {
			return webClient.get()
					.uri(webClientConfig.getClientesEndpoint() + "/{id}", clientId)
					.retrieve()
					.bodyToMono(ClienteDTO.class)
					.block();
		}catch (WebClientResponseException.NotFound e) {
			throw new ClienteNoEncontradoException(clientId);
		}catch (Exception e) {
			throw new ConexionServicioClienteException("No se pudo consultar el Cliente");
		}
	}

	@Override
	public Cuenta crearCuenta(Cuenta cuenta) {
		if(cuenta.getSaldoInicial() < 0) {
			throw new BadRequestException("No puede crear una cuenta con el saldo inicial negativo");
		}

		Long clienteId = cuenta.getClienteId();
		ClienteDTO cliente = obtenerClientePorId(clienteId);
		
		
		if(cliente == null) {
			throw new ClienteNoEncontradoException(clienteId);
		}

		return cuentaRepository.save(cuenta);
	}
	
	@Override
	public Cuenta actualizarCuenta(Long id, Cuenta cuenta) {
		cuenta.setId(id);
		return cuentaRepository.save(cuenta);
	}

	@Override
	public List<Cuenta> obtenerCuentasPorClienteId(Long clienteId) {
		return cuentaRepository.findByClienteId(clienteId);
	}

}
