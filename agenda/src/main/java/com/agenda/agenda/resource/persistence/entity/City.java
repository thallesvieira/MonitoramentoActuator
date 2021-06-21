package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.dto.response.CityDTOResponse;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class City extends BaseEntity {
	
	private static final long serialVersionUID = -5899446968200461769L;


	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_uf")
	private State state;
	
	public City() {}

	public City(Long id) {this.setId(id);}

	@Override
	public BaseDTOResponse toResponse() {
		CityDTOResponse response = new CityDTOResponse();

		response.setId(this.getId());
		response.setName(this.getName());
		response.setUF(this.getState().getUf());

		return response;
	}
}
