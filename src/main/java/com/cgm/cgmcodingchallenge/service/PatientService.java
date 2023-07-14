package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    private final PatientDAO patientDAO;

    public PatientService(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> fetchAll() {
        return null;
    }

    @Override
    public Patient fetch(String socialSecurityNumber) {
        return patientDAO.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public Patient create(Patient patient) {
        return patientDAO.save(patient);
    }
}
