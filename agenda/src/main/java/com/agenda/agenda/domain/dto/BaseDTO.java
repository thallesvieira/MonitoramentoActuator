package com.agenda.agenda.domain.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public abstract class BaseDTO {
    protected Long id;
 }
