package com.agenda.agenda.resource.persistence.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "scale")
@Data
public class Scale extends BaseEntity {
	
	private static final long serialVersionUID = -6407802772218448498L;
	

	private Integer week;
	
	private Boolean sunday;
	
	private Boolean monday;
	
	private Boolean tuesday;
	
	private Boolean wednesday;
	
	private Boolean thursday;
	
	private Boolean friday;
	
	private Boolean saturday;
	
	@Column(name = "morning_time_start")
	private LocalTime morningTimeStart;
	
	@Column(name = "morning_time_end")
	private LocalTime morningTimeEnd;
	
	@Column(name = "afternoon_time_start")
	private LocalTime afternoonTimeStart;
	
	@Column(name = "afternoon_time_end")
	private LocalTime afternoonTimeEnd;
	
	@Column(name = "recurrent_week")
	private Boolean recurrentWeek;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;
}
