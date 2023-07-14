package com.cgm.cgmcodingchallenge.service.interfaces;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;

import java.util.List;

public interface IPatientService {
    List<PatientDTO> fetchAll();
}
