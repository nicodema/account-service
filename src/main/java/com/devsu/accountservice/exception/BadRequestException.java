package com.devsu.accountservice.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1632101306696927383L;
	
	public BadRequestException(String mensaje) {
        super(mensaje);
    }

}
