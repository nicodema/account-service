package com.devsu.accountservice.exception;

public class ConexionServicioClienteException extends RuntimeException{

	private static final long serialVersionUID = -7622136551946747675L;

	public ConexionServicioClienteException(String mensaje) {
        super("Error al consultar el microservicio de Cliente. "+ mensaje);
    }
}
