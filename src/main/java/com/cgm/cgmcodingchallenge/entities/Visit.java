package com.cgm.cgmcodingchallenge.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    private Date startDate;
    private Date endDate;

    private VisitType visitType;

    private ReasonType reasonType;

    private String familyHistory;

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

enum VisitType{
    HOME, OFFICE
}

enum ReasonType{
    FIRST, RECURRING, URGENT
}
