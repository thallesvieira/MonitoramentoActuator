package com.agenda.agenda.resource.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.agenda.resource.persistence.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
