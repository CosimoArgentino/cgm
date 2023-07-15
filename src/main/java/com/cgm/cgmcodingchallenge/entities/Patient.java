package com.cgm.cgmcodingchallenge.entities;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private String socialSecurityNumber;

    @OneToMany(mappedBy = "patient")
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
}
