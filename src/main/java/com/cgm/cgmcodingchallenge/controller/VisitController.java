package com.cgm.cgmcodingchallenge.controller;

import com.cgm.cgmcodingchallenge.dto.VisitDTO;
import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.entities.Visit;
import com.cgm.cgmcodingchallenge.exceptions.OverlapVisitException;
import com.cgm.cgmcodingchallenge.exceptions.PatientNotFoundException;
import com.cgm.cgmcodingchallenge.exceptions.VisitNotFoundException;
import com.cgm.cgmcodingchallenge.service.interfaces.IVisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cgm/")
public class VisitController {

    private final IVisitService visitService;

    public VisitController(IVisitService visitService){
        this.visitService = visitService;
    }
    @PostMapping("/visits")
    public ResponseEntity<VisitDTO> create(@RequestBody VisitDTO visitDTO){
        try{
            Visit visit = visitService.create(visitDTO.toEntity());
            return new ResponseEntity<>(visit.toDto(), HttpStatus.CREATED);
        }catch(OverlapVisitException | PatientNotFoundException exc){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<VisitDTO> fetchVisit(@PathVariable String id){
        try{
            Visit visit = visitService.fetch(Long.parseLong(id));
            return new ResponseEntity<>(visit.toDto(), HttpStatus.OK);
        }catch(VisitNotFoundException exc){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/visits/{id}")
    public ResponseEntity<VisitDTO> update(@PathVariable String id, @RequestBody VisitDTO visitDTO){
        try {
            Visit visit = visitDTO.toEntity();
            visit.setVisitId(Long.parseLong(id));
            visit = visitService.update(visit);
            return new ResponseEntity<>(visit.toDto(), HttpStatus.OK);
        }catch(VisitNotFoundException | OverlapVisitException exc){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
