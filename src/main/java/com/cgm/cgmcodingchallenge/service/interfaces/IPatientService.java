package com.cgm.cgmcodingchallenge.service.interfaces;

import com.cgm.cgmcodingchallenge.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientService {
    Page<Patient> fetchAll(Pageable pageable);

    Patient fetch(String socialSecurityNumber);

    Patient create(Patient patient);

    Patient update(Patient patient);
}
