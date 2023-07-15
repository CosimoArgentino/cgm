package com.cgm.cgmcodingchallenge.dto;

import com.cgm.cgmcodingchallenge.entities.ReasonType;
import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.entities.VisitType;

import java.sql.Date;
import java.sql.Timestamp;

public class VisitDTO {
    private String socialSecurityNumber;

    private Timestamp startDate;
    private Timestamp endDate;

    private VisitType visitType;

    private ReasonType reasonType;

    private String familyHistory;

    public VisitDTO(String socialSecurityNumber, VisitType visitType,
                    ReasonType reasonType, Timestamp startDate, Timestamp endDate, String familyHistory) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.visitType = visitType;
        this.reasonType = reasonType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.familyHistory = familyHistory;
    }

    public Timestamp getStartDate() {
        return startDate;
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

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Visit toEntity(){
        return new Visit(this.socialSecurityNumber, this.startDate, this.endDate, this.visitType, this.reasonType, this.familyHistory);
    }
}
