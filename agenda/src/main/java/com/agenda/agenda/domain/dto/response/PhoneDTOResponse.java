package com.agenda.agenda.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTOResponse extends BaseDTOResponse {

    private String code;
    private String number;
    private String phoneType;
}
