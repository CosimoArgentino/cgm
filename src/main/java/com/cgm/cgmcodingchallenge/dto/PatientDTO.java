package com.cgm.cgmcodingchallenge.dto;

import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.entities.ReasonType;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

public class PatientDTO {
    private String name;
    private String surname;
    private Date birth;
    private String socialSecurityNumber;

    public PatientDTO(String name, String surname, Date birth, String socialSecurityNumber) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public PatientDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Patient toEntity(@NotNull PatientDTO patientDTO){
        return new Patient(patientDTO.name, patientDTO.surname, patientDTO.birth, patientDTO.socialSecurityNumber);
    }
}
