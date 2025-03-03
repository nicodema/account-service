package com.devsu.accountservice.model;

import java.util.ArrayList;
import java.util.List;

import com.devsu.accountservice.enums.TipoCuenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUENTAS", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"numero"})
})
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipo;
	private double saldoInicial;
	private boolean estado;
	
	private Long clienteId;
	
	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Movimiento> movimientos = new ArrayList<>();
	
	public Cuenta(String numero, TipoCuenta tipo, double saldoInicial, boolean estado, Long clienteId) {
		this.numero = numero;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.clienteId = clienteId;
	}
}
