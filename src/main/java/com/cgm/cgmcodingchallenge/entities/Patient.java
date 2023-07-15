package com.cgm.cgmcodingchallenge.entities;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @NotNull
    private String socialSecurityNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Visit> visits;
    private String name;
    private String surname;
    private Date birth;


    public Patient(String name, String surname, Date birth, String socialSecurityNumber) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Patient(){

    }

    public Patient(PatientBuilder patientBuilder) {
        this.name = patientBuilder.name;
        this.surname = patientBuilder.surname;
        this.socialSecurityNumber = patientBuilder.socialSecurityNumber;
        this.birth = patientBuilder.birth;
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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public PatientDTO toDto(){
        return new PatientDTO(this.name, this.surname, this.birth, this.socialSecurityNumber);
    }

    public static class PatientBuilder{

        private String socialSecurityNumber;

        private String name;
        private String surname;
        private Date birth;

        public PatientBuilder(){

        }

        public PatientBuilder setSocialSecurityNumber(String socialSecurityNumber){
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public PatientBuilder setName(String Name){
            this.name = name;
            return this;
        }

        public PatientBuilder setSurname(String surname){
            this.surname = surname;
            return this;
        }

        public PatientBuilder setBirth(Date birth){
            this.birth = birth;
            return this;
        }

        public Patient build(){
            return new Patient(this);
        }
    }
}
