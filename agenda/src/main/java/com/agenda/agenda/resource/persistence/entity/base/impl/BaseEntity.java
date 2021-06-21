package com.agenda.agenda.resource.persistence.entity.base.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.resource.persistence.entity.base.IBaseEntity;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable, IBaseEntity {

    private static final long serialVersionUID = 3779027956207925319L;
    private static ModelMapper mapper = new ModelMapper();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
        	return false;
        } else if (this == object) {
        	return true;
        } else if (!this.getClass().isAssignableFrom(object.getClass())) {
            return false;
        }

        BaseEntity other = (BaseEntity) object;
        return this.getId() != null && this.getId().equals(other.getId());
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }

    @Override
    public BaseEntity toEntity(BaseDTORequest request) {
        return null;
    }

    @Override
    public BaseDTOResponse toResponse() {
        return null;
    }

    @Override
    public BaseDTOResponse nonNullToResponse(BaseEntity response) {
        if (Objects.nonNull(response)){
            return response.toResponse();
        }

        return null;
    }

    @JsonIgnore
    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
        return true;
    }

    @JsonIgnore
    public Map<String, Object> getNonNullFields() throws IllegalAccessException {
        Map<String, Object> data = new HashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            if (!field.isAccessible())
                field.setAccessible(true);
            if (field.get(this) != null)
                data.put(field.getName(), field.get(this));
        }

        return data;
    }

}

