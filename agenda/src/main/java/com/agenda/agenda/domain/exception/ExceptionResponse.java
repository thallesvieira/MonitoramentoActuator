package com.agenda.agenda.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;


public class ExceptionResponse extends RuntimeException {
	
	private static final long serialVersionUID = 7635744926677754658L;
	
	@Getter
	private final HttpStatus status;
	@Getter
	private final String code;
	@Getter
	private final String message;
	
	public ExceptionResponse(String message) {
		super(message);
		this.status = null;
		this.message = "";
		this.code = "";
    }
	
	public ExceptionResponse(ExceptionResponseInterface error, HttpStatus status) {
		super(error.getMessage());
		
		this.code = error.getErrorCode();
		this.message = error.getMessage();
		this.status = status;
	}
	
	
}
