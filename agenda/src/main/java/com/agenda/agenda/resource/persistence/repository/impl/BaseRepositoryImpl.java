package com.agenda.agenda.resource.persistence.repository.impl;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.domain.repository.IBaseRepository;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public abstract class BaseRepositoryImpl<T extends BaseEntity, DTO extends BaseDTORequest, R extends JpaRepository<T, Long>> implements IBaseRepository<DTO> {
    protected final T entity;
    protected String[] ignoreProperties = {""};

    @Autowired
    protected R repository;

    public BaseRepositoryImpl(T entity, String... ignoreProperties) {
        this.entity = entity;
        this.ignoreProperties = ignoreProperties;
    }

    public BaseRepositoryImpl(T entity) {
        this.entity = entity;
    }

    Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    @Override
    public BaseDTOResponse toSave(BaseDTORequest dto) {
        try {
            T newEntity = (T) entity.toEntity(dto);
            return repository.save(newEntity).toResponse();
        } catch (Exception ex) {
            logger.error("Erro ao salvar entidade. " + ex.getMessage());
            throw new ExceptionResponse(ErrorEnum.B001, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BaseDTOResponse toUpdate(BaseDTORequest dto) {
        try {
            if (!repository.existsById(dto.getId())) {
            	throw new ExceptionResponse(ErrorEnum.B003, HttpStatus.NOT_FOUND);
            }

            T updateEntity = repository.findById(dto.getId()).get();
            BeanUtils.copyProperties(dto, updateEntity, ignoreProperties);

            return repository.save(updateEntity).toResponse();
        } catch (Exception ex) {
            logger.error("Erro ao atualizar entidade com id "+ dto.getId() + ". " + ex.getMessage());
            throw new ExceptionResponse(ErrorEnum.B004, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BaseDTOResponse toFindById(Long id) {
        return repository.findById(id).get().toResponse();
    }

}

