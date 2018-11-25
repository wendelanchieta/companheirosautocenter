package com.companheirosautocenter.appautocenter.resources.exceptions;

public class VeiculoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VeiculoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
