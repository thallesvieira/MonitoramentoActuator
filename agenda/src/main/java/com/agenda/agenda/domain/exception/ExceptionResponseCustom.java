package com.agenda.agenda.domain.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionResponseCustom {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String code;
	private String message;
	private String path;
}
