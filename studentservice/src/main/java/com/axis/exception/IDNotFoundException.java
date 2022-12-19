package com.axis.exception;

public class IDNotFoundException extends RuntimeException{
	
	public IDNotFoundException(String message) {
		super();
		this.message = message;
	}

	String message;
	
	public String getMessage() {
		return message;
	}

}
