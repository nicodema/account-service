package com.devsu.accountservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.accountservice.component.MovimientoMapper;
import com.devsu.accountservice.dto.ClienteDTO;
import com.devsu.accountservice.dto.CuentaMovimientoDTO;
import com.devsu.accountservice.dto.ReporteEstadoCuentaDTO;
import com.devsu.accountservice.exception.CuentaNoEncontradaException;
import com.devsu.accountservice.exception.SaldoNoDisponibleException;
import com.devsu.accountservice.model.Cuenta;
import com.devsu.accountservice.model.Movimiento;
import com.devsu.accountservice.repository.CuentaRepository;
import com.devsu.accountservice.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService{

	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private MovimientoMapper movimientoMapper;
	
	@Autowired
	private CuentaService cuentaService;

	@Override
	public Optional<Movimiento> obtenerPorId(Long id) {
		return movimientoRepository.findById(id);
	}

	@Override
	@Transactional
	public CuentaMovimientoDTO crearMovimiento(CuentaMovimientoDTO movimientoDTO) {
		
		String numeroCuenta = movimientoDTO.getNumeroCuenta();
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
				.orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
		
		double saldoInicial = cuenta.getSaldoInicial();
		double saldoDisponible = saldoInicial+movimientoDTO.getValor();
		
		if(saldoDisponible  < 0) {
			throw new SaldoNoDisponibleException("Saldo no disponible");
		}
		cuenta.setSaldoInicial(saldoDisponible);
		
		Movimiento movimiento = new Movimiento();
		movimiento.setValor(movimientoDTO.getValor());
		movimiento.setCuenta(cuenta);
		movimiento.setSaldo(saldoDisponible);
		movimiento.setFecha(LocalDate.now());
		movimiento.setTipo((movimiento.getValor()>0?"Deposito de ":"Retiro de ")+Math.abs(movimiento.getValor()));
		movimiento = movimientoRepository.save(movimiento);
		movimientoDTO = movimientoMapper.toDTO(movimiento);
		movimientoDTO.setSaldoInicial(saldoInicial);
		return movimientoDTO;
	}

	@Override
	public ReporteEstadoCuentaDTO generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
		ClienteDTO cliente = cuentaService.obtenerClientePorId(clienteId);
		ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
		reporte.setFecha(LocalDate.now());
		reporte.setNombreCliente(cliente.getNombre());
		
		List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
		
		List<ReporteEstadoCuentaDTO.CuentaDTO> cuentaDTOs = cuentas.stream()
				.map(cuenta -> {
					ReporteEstadoCuentaDTO.CuentaDTO cuentaDTO = new ReporteEstadoCuentaDTO.CuentaDTO();
					cuentaDTO.setNumeroCuenta(cuenta.getNumero());
					cuentaDTO.setTipoCuenta(cuenta.getTipo().toString());
					cuentaDTO.setSaldoDisponible(cuenta.getSaldoInicial());
					cuentaDTO.setEstado(cuenta.isEstado());
					
					List<Movimiento> movimientos = movimientoRepository.findByCuentaInAndFechaBetween(List.of(cuenta), fechaInicio, fechaFin);
					
					List<ReporteEstadoCuentaDTO.MovimientoDTO> movimientoDTOs = movimientos.stream().map(movimiento -> {
							ReporteEstadoCuentaDTO.MovimientoDTO movimientoDTO = new ReporteEstadoCuentaDTO.MovimientoDTO();
							movimientoDTO.setFecha(movimiento.getFecha());
							movimientoDTO.setTipoMovimiento(movimiento.getTipo());
							movimientoDTO.setValor(movimiento.getValor());
							movimientoDTO.setSaldo(movimiento.getSaldo());
							
							return movimientoDTO;
					})
				
				.collect(Collectors.toList());
					cuentaDTO.setMovimientos(movimientoDTOs);
					
					double saldoInicial = cuentaDTO.getSaldoDisponible() - movimientos.stream().mapToDouble(Movimiento::getValor).sum();
					cuentaDTO.setSaldoInicial(saldoInicial);
					return cuentaDTO;
				})
				.collect(Collectors.toList());
		
		
		
		reporte.setCuentas(cuentaDTOs);
		return reporte;
	}
	
}
