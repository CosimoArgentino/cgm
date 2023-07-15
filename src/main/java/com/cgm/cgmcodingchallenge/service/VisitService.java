package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.exceptions.OverlapVisitException;
import com.cgm.cgmcodingchallenge.exceptions.VisitNotFoundException;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.cgm.cgmcodingchallenge.repository.VisitDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IVisitService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class VisitService implements IVisitService {

    private final VisitDAO visitDAO;

    public VisitService(VisitDAO visitDAO){
        this.visitDAO = visitDAO;
    }
    @Override
    public Visit create(Visit visit) {
        if (isOverlap(visit.getStartDate(), visit.getEndDate(), null)){
            throw new OverlapVisitException(String.format("can't save visit, time overlap"));
        }
        Visit visitReturn = visitDAO.save(visit);
        visitReturn.getPatient().setSocialSecurityNumber(visit.getPatient().getSocialSecurityNumber());
        return visitReturn;
    }

    private boolean isOverlap(Timestamp startDate, Timestamp endDate, List<Long> visitIds){
        if(visitIds != null && visitIds.size() != 0){
            return visitDAO.findOverlappingDateExcludeSomeVisits(startDate, endDate, visitIds) != 0;
        }
        return visitDAO.findOverlappingDate(startDate, endDate) != 0;
    }

    @Override
    public Visit fetch(Long id) {
        return visitDAO.findById(id)
                .orElseThrow(()->new VisitNotFoundException(String.format("no visit found for id %d", id)));
    }

    @Override
    public Visit update(Visit visit) {
        if(isOverlap(visit.getStartDate(), visit.getEndDate(), Arrays.asList(visit.getVisitId()))){
            throw new OverlapVisitException(String.format("invalid time"));
        }

        visitDAO.findById(visit.getVisitId())
                .orElseThrow(()->new VisitNotFoundException(String.format("no visit found for id %d", visit.getVisitId())));

        return visitDAO.save(visit);
    }
}
