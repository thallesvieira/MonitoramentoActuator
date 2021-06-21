package com.agenda.agenda.domain.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AddressDTORequest extends BaseDTORequest {
	
	private String street;
	private String neighborhood;
	private String zipCode;
	private Integer number;
	private String complement;
	private Long idCity;

}
