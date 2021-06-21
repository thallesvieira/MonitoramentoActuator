package com.agenda.agenda.domain.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Component
public class UserDTOResponse extends BaseDTOResponse {
	
	private String name;
	private String username;
	private String cpf;
	private String email;
	private Boolean active;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	private List<PhoneDTOResponse> phones;
	private AddressDTOResponse address;
	//private List<CompanyDTOResponse> companies;
}
