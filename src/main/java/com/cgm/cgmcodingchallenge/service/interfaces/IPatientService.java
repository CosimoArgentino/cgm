package com.cgm.cgmcodingchallenge.service.interfaces;

import com.cgm.cgmcodingchallenge.entities.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> fetchAll();

    Patient fetch(String socialSecurityNumber);

    Patient create(Patient patient);
}
