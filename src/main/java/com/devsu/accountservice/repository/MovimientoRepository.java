package com.devsu.accountservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.accountservice.model.Cuenta;
import com.devsu.accountservice.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{

	List<Movimiento> findByCuentaInAndFechaBetween(List<Cuenta> cuentas, LocalDate fechaInicio, LocalDate fechaFin);
}
