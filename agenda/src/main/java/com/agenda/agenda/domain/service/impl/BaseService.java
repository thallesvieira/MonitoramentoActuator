package com.agenda.agenda.domain.service.impl;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.repository.IBaseRepository;
import com.agenda.agenda.domain.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService<R extends IBaseRepository, DTO extends BaseDTORequest> implements IBaseService<DTO> {

    @Autowired
    private R repository;

    @Override
    public BaseDTOResponse toFindById(Long id) {
        return repository.toFindById(id);
    }

    @Override
    public BaseDTOResponse toSave(DTO dto) {
        return repository.toSave(dto);
    }

    @Override
    public BaseDTOResponse toUpdate(DTO dto) {
        return repository.toUpdate(dto);
    }
}
