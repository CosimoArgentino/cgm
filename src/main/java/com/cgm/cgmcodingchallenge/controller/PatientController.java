package com.cgm.cgmcodingchallenge.controller;


import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.service.interfaces.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cgm")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> fetchAll(){
        List<PatientDTO> patients = patientService.fetchAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
