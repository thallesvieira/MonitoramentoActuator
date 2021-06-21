package com.agenda.agenda.domain.service.impl;

import com.agenda.agenda.domain.dto.request.UserDTORequest;
import com.agenda.agenda.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<IUserRepository, UserDTORequest> {

}
