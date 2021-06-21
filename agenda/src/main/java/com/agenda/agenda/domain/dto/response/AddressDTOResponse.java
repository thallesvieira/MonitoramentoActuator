package com.agenda.agenda.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTOResponse extends BaseDTOResponse {
    private String street;
    private String neighborhood;
    private String zipCode;
    private Integer number;
    private String complement;
    private CityDTOResponse city;
}
