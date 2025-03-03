package com.devsu.accountservice.exception;

public class SaldoNoDisponibleException extends RuntimeException{
	private static final long serialVersionUID = 2077622035287940576L;

	public SaldoNoDisponibleException(String mensaje) {
		super(mensaje);
	}
}
