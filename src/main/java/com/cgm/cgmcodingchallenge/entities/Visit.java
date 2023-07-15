package com.cgm.cgmcodingchallenge.entities;

import com.cgm.cgmcodingchallenge.dto.VisitDTO;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "social_security_number")
    private Patient patient;
    private Timestamp startDate;
    private Timestamp endDate;

    private VisitType visitType;

    private ReasonType reasonType;

    private String familyHistory;

    public Visit(){

    }

    public Visit(String socialSecurityNumber, Timestamp startDate, Timestamp endDate,
                 VisitType visitType, ReasonType reasonType, String familyHistory) {
        if(this.patient == null){
            this.patient = new Patient();
        }
        this.patient.setSocialSecurityNumber(socialSecurityNumber);
        this.startDate = startDate;
        this.endDate = endDate;
        this.visitType = visitType;
        this.reasonType = reasonType;
        this.familyHistory = familyHistory;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public VisitType getVisitType() {
        return visitType;
    }

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    public ReasonType getReasonType() {
        return reasonType;
    }

    public void setReasonType(ReasonType reasonType) {
        this.reasonType = reasonType;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public VisitDTO toDto(){
        return new VisitDTO(this.patient.getSocialSecurityNumber(), this.visitType, this.reasonType, this.startDate, this.endDate, this.familyHistory);
    }
}

