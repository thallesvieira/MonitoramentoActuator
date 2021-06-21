package com.agenda.agenda.resource.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.agenda.resource.persistence.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
