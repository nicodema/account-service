package com.devsu.accountservice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.accountservice.dto.ReporteEstadoCuentaDTO;
import com.devsu.accountservice.service.MovimientoService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
	@Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<ReporteEstadoCuentaDTO> generarEstadoCuenta(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam Long clienteId) {
    	ReporteEstadoCuentaDTO reporte = movimientoService.generarEstadoCuenta(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}
