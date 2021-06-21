package com.agenda.agenda.domain.validation;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.domain.util.Longs;
import org.springframework.http.HttpStatus;

public abstract class BaseValidation {
	
	public void validateSave(BaseDTORequest request) {
		
	}

	public void validateUpdate(BaseDTORequest request) {
		if (Longs.isNullOrZero(request.getId())) {
			throw new ExceptionResponse(ErrorEnum.B002, HttpStatus.BAD_REQUEST);
		}
	}

}
