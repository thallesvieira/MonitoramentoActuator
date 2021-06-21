package com.agenda.agenda.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class DefaultResponseStatusException extends ResponseStatusException {

	private static final long serialVersionUID = -8287326596476811199L;

	public DefaultResponseStatusException(HttpStatus httpStatus, String message, Object value) {
        super(httpStatus, String.format(message, value));
    }
}
