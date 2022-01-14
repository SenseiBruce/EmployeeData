package com.example.springjpa.exceptionhandlers;

public class ResourceAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2981472472482386820L;

	public ResourceAlreadyExistsException(String message) {
		super(message);
		
	}

	
}
