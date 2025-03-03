package com.devsu.accountservice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CuentaMovimientoDTO {
	private LocalDate fecha;
	private String nombreCliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Double valor;
    private String tipoMovimiento;
    private Double saldoDisponible;
}
