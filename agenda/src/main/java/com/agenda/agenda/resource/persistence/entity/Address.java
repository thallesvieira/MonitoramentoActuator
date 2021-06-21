package com.agenda.agenda.resource.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agenda.agenda.domain.dto.request.AddressDTORequest;
import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.response.AddressDTOResponse;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.dto.response.CityDTOResponse;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import com.agenda.agenda.resource.persistence.entity.base.IBaseEntity;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address extends BaseEntity {
	
	private static final long serialVersionUID = -984200853633411192L;

	private String street;
	
	private String neighborhood;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	private Integer number;
	
	private String complement;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private City city;

	@Override
	public BaseEntity toEntity(BaseDTORequest dto) {
		AddressDTORequest request = (AddressDTORequest) dto;

		Address address = new Address();

		address.setId(request.getId());
		address.setStreet(request.getStreet());
		address.setNeighborhood(request.getNeighborhood());
		address.setZipCode(request.getZipCode());
		address.setNumber(request.getNumber());
		address.setComplement(request.getComplement());
		address.setCity(new City(request.getIdCity()));

		return address;
	}

	@Override
	public BaseDTOResponse toResponse() {
		AddressDTOResponse response = new AddressDTOResponse();

		response.setId(this.getId());
		response.setStreet(this.getStreet());
		response.setNeighborhood(this.getNeighborhood());
		response.setZipCode(this.getZipCode());
		response.setNumber(this.getNumber());
		response.setComplement(this.getComplement());
		response.setCity((CityDTOResponse) this.city.toResponse());

		return response;
	}
}
