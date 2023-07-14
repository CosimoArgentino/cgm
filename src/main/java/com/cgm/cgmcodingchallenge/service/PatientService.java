package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.exceptions.InvalidSecurityNumberException;
import com.cgm.cgmcodingchallenge.exceptions.PatientNotFoundException;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IPatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientService implements IPatientService {

    private final PatientDAO patientDAO;

    private final Pattern pattern = Pattern.compile("^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$");

    public PatientService(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> fetchAll() {
       return patientDAO.findAll();
    }

    @Override
    public Patient fetch(String socialSecurityNumber) {
        return patientDAO.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public Patient create(Patient patient) {
        Matcher matcher = pattern.matcher(patient.getSocialSecurityNumber());
        if(!matcher.matches()) {
            throw new InvalidSecurityNumberException(String.format("invalid security number %s", patient.getSocialSecurityNumber()));
        }
        return patientDAO.save(patient);
    }

    @Override
    public Patient update(Patient patientToUpdate) {
        Patient patient = patientDAO.findBySocialSecurityNumber(patientToUpdate.getSocialSecurityNumber());
        if(patient == null){
            throw new PatientNotFoundException(String.format("patient %s not found"));
        }
        patient.setName(patientToUpdate.getName());
        patient.setBirth(patientToUpdate.getBirth());
        patient.setSurname(patientToUpdate.getSurname());
        patient.setSocialSecurityNumber(patient.getSocialSecurityNumber());
        return patientDAO.save(patient);
    }
}
