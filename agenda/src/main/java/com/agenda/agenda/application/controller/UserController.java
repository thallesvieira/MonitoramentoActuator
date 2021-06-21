package com.agenda.agenda.application.controller;

import com.agenda.agenda.domain.dto.request.UserDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.dto.response.UserDTOResponse;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.domain.service.impl.UserService;
import com.agenda.agenda.domain.validation.UserValidation;
import com.agenda.agenda.security.service.TokenService;
import com.sun.istack.Nullable;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController extends BaseCrudController<UserDTORequest> {

	@Autowired
	private UserService service;

	@Autowired
	private UserValidation validation;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TokenService tokenService;

	public UserController(UserService service, UserValidation validation) {
		super(service, validation);
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Retorna os dados do usuário pelo id informado.", authorizations = { @Authorization(value = "Bearer") })
	@Override
	public ResponseEntity<UserDTOResponse> get(@Nullable @PathVariable Long id) {
		try {
			Long userId = tokenService.getIdUser(tokenService.getToken(request));
			return ResponseEntity.ok((UserDTOResponse) service.toFindById(userId));
		} catch (Exception e) {
			throw new ExceptionResponse(ErrorEnum.B003, HttpStatus.NOT_FOUND);
		}
	}

    @PostMapping("/")
    @Override
	public ResponseEntity<UserDTOResponse> save(@RequestBody UserDTORequest dto) {
		validation.validateSave(dto);

		return ResponseEntity.ok((UserDTOResponse) service.toSave(dto));
	}


	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza os campos da entidade.", authorizations = { @Authorization(value = "Bearer") })
	public ResponseEntity<UserDTOResponse> update(@RequestBody UserDTORequest dto) {
		validation.validateSave(dto);

		UserDTOResponse response = (UserDTOResponse) service.toUpdate(dto);
		return ResponseEntity.ok(response);
	}

}
