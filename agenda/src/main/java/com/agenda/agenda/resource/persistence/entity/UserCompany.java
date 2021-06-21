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
@Table(name = "user_company")
@Data
public class UserCompany extends BaseEntity {
	
	
	private static final long serialVersionUID = -6975801778620798938L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;

}
