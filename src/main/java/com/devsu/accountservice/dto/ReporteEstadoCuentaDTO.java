package com.devsu.accountservice.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ReporteEstadoCuentaDTO {
	private LocalDate fecha;
	private String nombreCliente;
    private List<CuentaDTO> cuentas;

    @Data
    public static class CuentaDTO {
        private String numeroCuenta;
        private String tipoCuenta;
        private double saldoInicial;
        private boolean estado;
        private Double saldoDisponible;
        private List<MovimientoDTO> movimientos;
    }

    @Data
    public static class MovimientoDTO {
        private LocalDate fecha;
        private String tipoMovimiento;
        private double valor;
        private double saldo;
    }

}
