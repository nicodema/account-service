package com.devsu.accountservice.component;

import org.springframework.stereotype.Component;

import com.devsu.accountservice.dto.CuentaMovimientoDTO;
import com.devsu.accountservice.model.Movimiento;

@Component
public class MovimientoMapper {
	public Movimiento toEntity(CuentaMovimientoDTO movimientoDTO) {
		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(movimientoDTO.getFecha());
		movimiento.setTipo(movimientoDTO.getTipoMovimiento());
		movimiento.setValor(movimientoDTO.getValor());
		movimiento.setSaldo(movimientoDTO.getSaldoDisponible());
		return movimiento;
	}
	
	public CuentaMovimientoDTO toDTO(Movimiento movimiento) {
		CuentaMovimientoDTO dto = new CuentaMovimientoDTO();
		dto.setFecha(movimiento.getFecha());
		dto.setTipoMovimiento(movimiento.getTipo());
		dto.setValor(movimiento.getValor());
		dto.setSaldoDisponible(movimiento.getSaldo());
		
		if(movimiento.getCuenta() != null) {
			dto.setNumeroCuenta(movimiento.getCuenta().getNumero());
			dto.setTipoCuenta(movimiento.getCuenta().getTipo().toString());
			dto.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
			dto.setEstado(movimiento.getCuenta().isEstado());
		}
		return dto;
	}
}
