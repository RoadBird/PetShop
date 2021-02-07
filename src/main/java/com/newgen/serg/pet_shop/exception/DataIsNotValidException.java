package com.newgen.serg.pet_shop.exception;

public class DataIsNotValidException extends Exception{
	private static final long serialVersionUID = 1L;

	public DataIsNotValidException(String message) {
		super(message);
	}

	public DataIsNotValidException(String message, Throwable ex) {
		super(message, ex);
	}
	
}
