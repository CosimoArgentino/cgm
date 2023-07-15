package com.cgm.cgmcodingchallenge.service.interfaces;

import com.cgm.cgmcodingchallenge.entities.Visit;

public interface IVisitService {
    Visit create(Visit visit);

    Visit fetch(Long id);

    Visit update(Visit visit);
}
