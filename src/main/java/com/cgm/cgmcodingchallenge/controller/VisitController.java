package com.cgm.cgmcodingchallenge.controller;

import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.service.interfaces.IVisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cgm/")
public class VisitController {

    private final IVisitService visitService;

    public VisitController(IVisitService visitService){
        this.visitService = visitService;
    }
    @PostMapping("/visits")
    public ResponseEntity<Visit> create(@RequestBody Visit visit){
        visitService.create(visit);
        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }
}
