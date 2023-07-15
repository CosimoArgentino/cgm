package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.exceptions.InvalidSecurityNumberException;
import com.cgm.cgmcodingchallenge.exceptions.PatientNotFoundException;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientService implements IPatientService {

    private final static Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientDAO patientDAO;

    private final Pattern pattern = Pattern.compile("^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$");

    public PatientService(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @Override
    public Page<Patient> fetchAll(Pageable pageable) {
       return patientDAO.findAll(pageable);
    }

    @Override
    public Patient fetch(String socialSecurityNumber) {
        return patientDAO.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public Patient create(Patient patient) {
        Matcher matcher = pattern.matcher(patient.getSocialSecurityNumber());
        if(!matcher.matches()) {
            String message = String.format("invalid social security number %s", patient.getSocialSecurityNumber());
            logger.error(message);
            throw new InvalidSecurityNumberException(message);
        }
        return patientDAO.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        String socialSecurityNumber = patient.getSocialSecurityNumber();
        Patient patientToUpdate = patientDAO.findBySocialSecurityNumber(socialSecurityNumber);
        if(patientToUpdate == null){
            String message = String.format("patient % not found", socialSecurityNumber);
            logger.error(message);
            throw new PatientNotFoundException(message);
        }
        return patientDAO.save(patient);
    }
}
