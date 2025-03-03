package com.devsu.accountservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsu.accountservice.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

	@Query("SELECT c FROM Cuenta c WHERE c.numero = :numero")
	Optional<Cuenta> findByNumeroCuenta(@Param("numero") String numeroCuenta);
	
	@Query("SELECT c FROM Cuenta c WHERE c.clienteId = :clienteId")
	List<Cuenta> findByClienteId(@Param("clienteId")Long clienteId);
	
	//List<Cuenta> findByClienteId(Long clienteId);
}
