package com.agenda.agenda.resource.persistence.repository.impl;

import com.agenda.agenda.domain.dto.request.BaseDTORequest;
import com.agenda.agenda.domain.dto.request.UserDTORequest;
import com.agenda.agenda.domain.dto.response.BaseDTOResponse;
import com.agenda.agenda.domain.exception.ErrorEnum;
import com.agenda.agenda.domain.exception.ExceptionResponse;
import com.agenda.agenda.domain.repository.IUserRepository;
import com.agenda.agenda.resource.persistence.entity.Address;
import com.agenda.agenda.resource.persistence.entity.Phone;
import com.agenda.agenda.resource.persistence.entity.User;
import com.agenda.agenda.resource.persistence.repository.AddressRepository;
import com.agenda.agenda.resource.persistence.repository.CityRepository;
import com.agenda.agenda.resource.persistence.repository.PhoneRepository;
import com.agenda.agenda.resource.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserDTORequest, UserRepository> implements IUserRepository {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    public UserRepositoryImpl() {
        super(new User(), "password", "cpf");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseDTOResponse toSave(BaseDTORequest dto) {
        try {
            User user = (User) new User().toEntity(dto);

            if (user.getAddress() != null) {
                user.setAddress(addressRepository.save(user.getAddress()));
                Long idCity = user.getAddress().getCity().getId();
                user.getAddress().setCity(cityRepository.findById(idCity).get());
            }

            user = repository.save(user);
            if (user.getPhones() != null && !user.getPhones().isEmpty()) {
                ArrayList<Phone> phones = new ArrayList();
                User finalUser = user;
                user.getPhones().forEach(it -> {
                    it.setUser(finalUser);
                    phones.add(phoneRepository.save(it));
                });

                user.setPhones(phones);
            }

            return user.toResponse();
        } catch (Exception ex) {
            logger.error("Erro ao salvar entidade. " + ex.getMessage());
            throw new ExceptionResponse(ErrorEnum.B001, HttpStatus.BAD_REQUEST);
        }
    }

}
