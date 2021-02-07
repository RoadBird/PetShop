package com.newgen.serg.pet_shop.exception;

public class UnauthorizedException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}
	
}
