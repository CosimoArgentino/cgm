package com.cgm.cgmcodingchallenge;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.dto.VisitDTO;
import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.entities.ReasonType;
import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.entities.VisitType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TestUtils {
    public static String toJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PatientDTO createPatientDto(){
       return createPatient().toDto();
    }

    public static Patient createPatient(){
        return new Patient.PatientBuilder()
                .setName("Name")
                .setSurname("Surname")
                .setBirth(Date.valueOf("1991-12-21"))
                .setSocialSecurityNumber("SRNNMA91T21L049Z")
                .build();
    }

    public static VisitDTO createVisitDto(){
        return createVisit().toDto();
    }

    public static Visit createVisit(){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp plusHour = Timestamp.valueOf(LocalDateTime.now().plus(60, ChronoUnit.MINUTES));
        return new Visit.VisitBuilder()
                .setStartDate(now)
                .setEndDate(plusHour)
                .setFamilyHistory("none")
                .setVisitType(VisitType.HOME)
                .setReasonType(ReasonType.URGENT)
                .setSocialSecurityNumber("SRNNMA91T21L049Z")
                .build();
    }


}
