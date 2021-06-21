package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "company_type")
@Data
public class CompanyType extends BaseEntity {

	private static final long serialVersionUID = -9024611622751450914L;

	private String name;

}
