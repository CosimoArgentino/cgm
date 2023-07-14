package com.cgm.cgmcodingchallenge.service;

import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.repository.VisitDAO;
import com.cgm.cgmcodingchallenge.service.interfaces.IVisitService;
import org.springframework.stereotype.Service;

@Service
public class VisitService implements IVisitService {

    private final VisitDAO visitDAO;

    public VisitService(VisitDAO visitDAO){
        this.visitDAO = visitDAO;
    }
    @Override
    public Visit create(Visit visit) {
        return visitDAO.save(visit);
    }
}
