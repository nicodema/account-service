package com.devsu.accountservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MOVIMIENTOS")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
	private String tipo;
	private double valor;
	private double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuenta_id", nullable = false)
	private Cuenta cuenta;
}
