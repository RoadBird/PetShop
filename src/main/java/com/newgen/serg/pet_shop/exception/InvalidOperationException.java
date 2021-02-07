package com.newgen.serg.pet_shop.exception;

public class InvalidOperationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidOperationException(String message) {
		super(message);
	}

	public InvalidOperationException(String message, Throwable ex) {
		super(message, ex);
	}
	
	
}
