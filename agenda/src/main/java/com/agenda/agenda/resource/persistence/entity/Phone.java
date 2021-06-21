package com.agenda.agenda.resource.persistence.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.request.PhoneDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.dto.response.PhoneDTOResponse;
import com.agenda.agenda.resource.persistence.entity.base.impl.BaseEntity;
import com.agenda.agenda.resource.persistence.entity.base.IBaseEntity;
import com.agenda.agenda.resource.persistence.entity.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "phone")
@Getter
@Setter
public class Phone extends BaseEntity {

	private static final long serialVersionUID = -7012483995423495117L;
	

	private String code;
	
	private String number;
	
	private PhoneType phoneType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;


	@Override
	public BaseEntity toEntity(BaseDTORequest dto) {
		PhoneDTORequest request = (PhoneDTORequest) dto;

		Phone phone = new Phone();

		phone.setCode(request.getCode());
		phone.setId(request.getId());
		phone.setNumber(request.getNumber());
		phone.setPhoneType(PhoneType.valueOf(request.getPhoneType()));

		return phone;
	}

	@Override
	public BaseDTOResponse toResponse() {
		PhoneDTOResponse response = new PhoneDTOResponse();

		response.setCode(this.getCode());
		response.setId(this.getId());
		response.setNumber(this.getNumber());
		response.setPhoneType(this.getPhoneType().toString());

		return response;
	}

	public List<Phone> toListEntity(List<PhoneDTORequest> phonesDTO) {
		if (Objects.isNull(phonesDTO) || phonesDTO.isEmpty()) return null;

		return phonesDTO.stream().map(map -> (Phone) toEntity(map)).collect(Collectors.toList());
	}

	public List<PhoneDTOResponse> toListResponse(List<Phone> phones) {
		if (Objects.isNull(phones) || phones.isEmpty()) return null;

		return phones.stream().map(map -> (PhoneDTOResponse) map.toResponse()).collect(Collectors.toList());
	}

}
