package com.agenda.agenda.resource.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class Company extends BaseEntity {
	
	private static final long serialVersionUID = 1663176945599693947L;


	private String cnpj;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "fantasy_name")
	private String fantasyName;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_id")
	private  List<Phone> phones;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_service",
			joinColumns = @JoinColumn(name = "service_id"),
			inverseJoinColumns = @JoinColumn(name = "company_id"))
	private List<Service> services;
	
	@ManyToMany(mappedBy = "companies")
	private List<User> users;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_type_id")
	private CompanyType companyType;

}
