package com.agenda.agenda.domain.service;

import com.agenda.agenda.domain.dto.response.BaseDTOResponse;

public interface IBaseService<DTO> {
    BaseDTOResponse toFindById(Long id);
    BaseDTOResponse toSave(DTO dto);
    BaseDTOResponse toUpdate(DTO dto);
}
