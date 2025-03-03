package com.devsu.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.accountservice.dto.ClienteDTO;
import com.devsu.accountservice.model.Cuenta;
import com.devsu.accountservice.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long clienteId) {
		ClienteDTO cliente = cuentaService.obtenerClientePorId(clienteId);
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
		Cuenta nCuenta = cuentaService.crearCuenta(cuenta);
		return ResponseEntity.ok(nCuenta);
	}

}
