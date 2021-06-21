package com.agenda.agenda.resource.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "service")
@Data
public class Service extends BaseEntity {
	
	private static final long serialVersionUID = 8642513833707467624L;


	private String name;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@ManyToMany(mappedBy = "services")
	private List<Company> companies;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company created_company;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_type_id")
	private CompanyType companyType;

}
