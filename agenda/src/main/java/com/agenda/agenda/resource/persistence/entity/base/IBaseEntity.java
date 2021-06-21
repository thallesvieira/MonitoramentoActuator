package com.agenda.agenda.resource.persistence.entity.base;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;

public interface IBaseEntity {
     BaseEntity toEntity(BaseDTORequest dto);
     BaseDTOResponse toResponse();
     BaseDTOResponse nonNullToResponse(BaseEntity response);
}
