package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile extends BaseEntity {

	private static final long serialVersionUID = 8466990002694630586L;

}
