package com.agenda.agenda.application.controller;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.domain.service.impl.BaseService;
import com.agenda.agenda.domain.validation.BaseValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("rawtypes")
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "*")
public abstract class BaseCrudController<DTO extends BaseDTORequest>{
    protected final BaseService service;
    protected final BaseValidation validation;

    public BaseCrudController(BaseService service, BaseValidation validation) {
        this.service = service;
        this.validation = validation;
    }


    @SuppressWarnings({ "unchecked" })
    @GetMapping("/{id}")
    @ApiOperation(value="Retorna a entidade pelo id informado.", authorizations = { @Authorization(value = "Bearer") })   
    public ResponseEntity get(@PathVariable Long id) {
        try {
            BaseDTOResponse response = service.toFindById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ExceptionResponse(ErrorEnum.B003, HttpStatus.NOT_FOUND);
        }
    }


    @SuppressWarnings({ "unchecked" })
    @PostMapping("/")
    @ApiOperation(value="Insere entidade na base de dados", authorizations = { @Authorization(value = "Bearer") })
    public ResponseEntity save(@RequestBody DTO dto) {
        validation.validateSave(dto);

        BaseDTOResponse response = service.toSave(dto);
        return ResponseEntity.ok(response);
    }

    @SuppressWarnings({ "unchecked" })
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza os campos da entidade.", authorizations = { @Authorization(value = "Bearer") })
    public ResponseEntity update(@RequestBody DTO dto) {
        validation.validateUpdate(dto);

        BaseDTOResponse response = service.toUpdate(dto);
        return ResponseEntity.ok(response);
    }

}
