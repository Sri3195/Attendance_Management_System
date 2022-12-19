package com.axis.exception;

public class InvalidIdException extends RuntimeException{
	
	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	String message;
	
	public String getMessage() {
		return message;
	}

}
