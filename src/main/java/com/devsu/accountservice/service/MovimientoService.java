package com.devsu.accountservice.service;

import java.time.LocalDate;
import java.util.Optional;

import com.devsu.accountservice.dto.CuentaMovimientoDTO;
import com.devsu.accountservice.dto.ReporteEstadoCuentaDTO;
import com.devsu.accountservice.model.Movimiento;

public interface MovimientoService {
	Optional<Movimiento> obtenerPorId(Long id);
	CuentaMovimientoDTO crearMovimiento(CuentaMovimientoDTO movimientoDTO);
	ReporteEstadoCuentaDTO generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
	
}
