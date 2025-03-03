package com.devsu.accountservice.exception;

public class ClienteNoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 5881860641274280123L;
	
	public ClienteNoEncontradoException(Long id) {
        super("No se encontro un cliente con el ID: " + id);
    }
}
