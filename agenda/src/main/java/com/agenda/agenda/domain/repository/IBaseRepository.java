package com.agenda.agenda.domain.repository;

import com.agenda.agenda.domain.dto.response.BaseDTOResponse;

public interface IBaseRepository<DTO> {
    BaseDTOResponse toFindById(Long id);
    BaseDTOResponse toSave(DTO dto);
    BaseDTOResponse toUpdate(DTO dto);
}