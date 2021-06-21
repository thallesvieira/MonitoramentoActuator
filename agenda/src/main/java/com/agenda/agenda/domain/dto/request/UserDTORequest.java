package com.agenda.agenda.domain.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class UserDTORequest extends BaseDTORequest {
	
	private String name;
	private String username;
	private String password;
	private String cpf;
	private String email;
	private List<PhoneDTORequest> phones;	
	private AddressDTORequest address;
}
