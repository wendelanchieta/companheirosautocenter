package com.companheirosautocenter.appautocenter.resources.exceptions;

public class VeiculoIdMismatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VeiculoIdMismatchException(String msg) {
		super(msg);
	}
	
	public VeiculoIdMismatchException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
