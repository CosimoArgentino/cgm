package com.cgm.cgmcodingchallenge.entities;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"socialSecurityNumber"})})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PatientDTO toDto(){
        return new PatientDTO(this.name, this.surname, this.birth, this.socialSecurityNumber);
    }
}
