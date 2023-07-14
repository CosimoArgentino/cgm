package com.cgm.cgmcodingchallenge.entities;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"socialSecurityNumber"})})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patientId;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;
    private String name;
    private String surname;
    private Date birth;
    private String socialSecurityNumber;

    public Patient(String name, String surname, Date birth, String socialSecurityNumber) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Patient(){

    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public PatientDTO toDto(){
        return new PatientDTO(this.name, this.surname, this.birth, this.socialSecurityNumber);
    }
}
