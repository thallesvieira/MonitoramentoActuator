package com.agenda.agenda.domain.validation;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.request.UserDTORequest;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserValidation extends BaseValidation {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Override
    public void validateSave(BaseDTORequest request) {
        UserDTORequest dto = (UserDTORequest) request;

    }

    @Override
    public void validateUpdate(BaseDTORequest request) {
        UserDTORequest dto = (UserDTORequest) request;

        validateId(dto.getId());
    }

    private void validateId(Long id) {
        String token = tokenService.getToken(request);
        Long userId = tokenService.getIdUser(token);

        if (id.compareTo(userId) != 0)
            throw new ExceptionResponse(ErrorEnum.USU001, HttpStatus.BAD_REQUEST);
    }
}
