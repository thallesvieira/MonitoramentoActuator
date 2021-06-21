package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "state")
@Data
public class State {
	
	@Id
	private String uf;
	
	private String name;

}
