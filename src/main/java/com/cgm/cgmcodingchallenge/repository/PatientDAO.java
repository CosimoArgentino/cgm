package com.cgm.cgmcodingchallenge.repository;

import com.cgm.cgmcodingchallenge.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Long> {
    Patient findBySocialSecurityNumber(String socialSecurityNumber);

}
