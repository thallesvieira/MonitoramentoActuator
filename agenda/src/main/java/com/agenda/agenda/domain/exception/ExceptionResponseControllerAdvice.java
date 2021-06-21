package com.agenda.agenda.domain.exception;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResponseControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ExceptionResponse.class)
	public final ResponseEntity<ExceptionResponseCustom> internalException(ExceptionResponse ex, HttpServletRequest request) {
		
		ExceptionResponseCustom errorHandling = new ExceptionResponseCustom();
		
		errorHandling.setTimestamp(LocalDateTime.now());
		errorHandling.setError(ex.getStatus().name());
		errorHandling.setStatus(ex.getStatus().value());
		errorHandling.setCode(ex.getCode());
		errorHandling.setMessage(ex.getMessage());
		errorHandling.setPath(request.getRequestURI());
		
		return new ResponseEntity<>(errorHandling, ex.getStatus());
	
	}
}
