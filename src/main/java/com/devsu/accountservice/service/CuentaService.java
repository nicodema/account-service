package com.devsu.accountservice.service;

import java.util.List;
import java.util.Optional;

import com.devsu.accountservice.dto.ClienteDTO;
import com.devsu.accountservice.model.Cuenta;

public interface CuentaService {
	Optional<Cuenta> obtenerPorNumeroCuenta(String numeroCuenta);
	Cuenta crearCuenta(Cuenta cuenta);
	Cuenta actualizarCuenta(Long id, Cuenta cuenta);
	ClienteDTO obtenerClientePorId(Long id);
	List<Cuenta> obtenerCuentasPorClienteId(Long clienteId);
}
