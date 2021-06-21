package com.agenda.agenda.resource.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "company_service")
@Data
public class CompanyService extends BaseEntity {
	
	private static final long serialVersionUID = 5071282324476991440L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_id")
	private Service service;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name = "duration_days")
	private Integer durationDays;
	
	@Column(name = "duration_hours")
	private Integer durationHours;
	
	@Column(name = "duration_minutes")
	private Integer durationMinutes;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
}
