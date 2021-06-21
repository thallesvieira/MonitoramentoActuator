package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "scale_service")
@Data
public class ScaleService extends BaseEntity {
	
	private static final long serialVersionUID = -6173052878222538271L;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scale_id")
	private Scale scale;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service service;

}
