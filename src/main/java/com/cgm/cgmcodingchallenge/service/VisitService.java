package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.exceptions.OverlapVisitException;
import com.cgm.cgmcodingchallenge.exceptions.PatientNotFoundException;
import com.cgm.cgmcodingchallenge.exceptions.VisitNotFoundException;
import com.cgm.cgmcodingchallenge.repository.VisitDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class VisitService implements IVisitService {

    private final static Logger logger = LoggerFactory.getLogger(VisitService.class);

    private final VisitDAO visitDAO;

    public VisitService(VisitDAO visitDAO){
        this.visitDAO = visitDAO;
    }
    @Override
    public Visit create(Visit visit) {
        if (isOverlap(visit.getStartDate(), visit.getEndDate(), null)){
            String message = String.format("time overlap %s and %s, patient %s", visit.getStartDate().toString(), visit.getEndDate().toString(), visit.getPatient().getSocialSecurityNumber());
            logger.error(message);
            throw new OverlapVisitException(message);
        }
        try {
            Visit visitReturn = visitDAO.save(visit);
            visitReturn.getPatient().setSocialSecurityNumber(visit.getPatient().getSocialSecurityNumber());
            return visitReturn;
        }catch(RuntimeException exc){
            String message = String.format("patient not found for social security number %s", visit.getPatient().getSocialSecurityNumber());
            logger.error(message);
            throw new PatientNotFoundException(message);
        }
    }

    @Override
    public Visit fetch(Long id) {
        return visitDAO.findById(id)
                .orElseThrow(()->new VisitNotFoundException(String.format("no visit found for id %d", id)));
    }

    @Override
    public Visit update(Visit visit) {
        visitDAO.findById(visit.getVisitId())
                .orElseThrow(()->new VisitNotFoundException(String.format("no visit found for id %d", visit.getVisitId())));

        if(isOverlap(visit.getStartDate(), visit.getEndDate(), Arrays.asList(visit.getVisitId()))){
            String message = String.format("time overlap %s and %s, patient %s", visit.getStartDate().toString(), visit.getEndDate().toString(), visit.getPatient().getSocialSecurityNumber());
            logger.error(message);
            throw new OverlapVisitException(message);
        }

        return visitDAO.save(visit);
    }


    private boolean isOverlap(Timestamp startDate, Timestamp endDate, List<Long> visitIds){
        if(visitIds != null && visitIds.size() != 0){
            return visitDAO.findOverlappingDateExcludeSomeVisits(startDate, endDate, visitIds) != 0;
        }
        return visitDAO.findOverlappingDate(startDate, endDate) != 0;
    }
}
