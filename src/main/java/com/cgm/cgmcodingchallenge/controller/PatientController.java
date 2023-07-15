package com.cgm.cgmcodingchallenge.controller;


import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.exceptions.InvalidSecurityNumberException;
import com.cgm.cgmcodingchallenge.exceptions.PatientNotFoundException;
import com.cgm.cgmcodingchallenge.service.interfaces.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cgm")
public class PatientController {

    private final IPatientService patientService;
    public PatientController(IPatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> fetchAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = patientService.fetchAll(pageable);
        return new ResponseEntity<>(patients
                .stream()
                .map(p->p.toDto()).toList(), HttpStatus.OK);
    }

    @GetMapping("/patients/{socialSecurityNumber}")
    public ResponseEntity<PatientDTO> fetch(@PathVariable String socialSecurityNumber) {
        Patient patient = patientService.fetch(socialSecurityNumber);
        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient.toDto(), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO){
        try {
            Patient patient = patientService.create(patientDTO.toEntity(patientDTO));
            return new ResponseEntity<>(patient.toDto(), HttpStatus.CREATED);
        }catch(InvalidSecurityNumberException exc){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/patients/{socialSecurityNumber}")
    public ResponseEntity<PatientDTO> update(@PathVariable String socialSecurityNumber, @RequestBody PatientDTO patientDTO){
        try {
            patientDTO.setSocialSecurityNumber(socialSecurityNumber);
            Patient patient = patientService.update(patientDTO.toEntity(patientDTO));
            return new ResponseEntity<>(patient.toDto(), HttpStatus.OK);
        }catch(PatientNotFoundException exc){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
