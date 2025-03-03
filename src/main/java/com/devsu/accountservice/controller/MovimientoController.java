package com.devsu.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.accountservice.dto.CuentaMovimientoDTO;
import com.devsu.accountservice.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;

	@PostMapping
	public ResponseEntity<CuentaMovimientoDTO> crearMovimiento(@RequestBody CuentaMovimientoDTO movimientoDTO) {
		CuentaMovimientoDTO nMovimiento = movimientoService.crearMovimiento(movimientoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(nMovimiento);
	}

}
