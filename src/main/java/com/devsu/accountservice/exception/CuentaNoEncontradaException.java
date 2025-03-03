package com.devsu.accountservice.exception;

public class CuentaNoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 8492548644504281202L;

	public CuentaNoEncontradaException(String numero) {
        super("No se encontro una Cuenta con el Número: " + numero);
    }
}
