package com.example.springjpa.exceptionhandlers;

public class ProjectNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3634625108080341186L;
	
	public ProjectNotFoundException(String message) {
		super(message);
	}
}
