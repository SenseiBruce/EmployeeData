package com.example.springjpa.exceptionhandlers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CustomizedExceptionHandler.class);
	 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)  {
		LOGGER.info(ex.toString());
		ExceptionResponse exceptionResponse= 
				new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false) );
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * @ExceptionHandler(EmployeeNotFoundException.class) public final
	 * ResponseEntity<Object>
	 * handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest
	 * request) {
	 * 
	 * ExceptionResponse exceptionResponse= new ExceptionResponse(new
	 * Date(),ex.getMessage(), request.getDescription(false) ); return new
	 * ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
