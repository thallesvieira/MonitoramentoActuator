package com.agenda.agenda.domain.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PhoneDTORequest extends BaseDTORequest {
	
	private String code;
	private String number;
	private String phoneType;
}
