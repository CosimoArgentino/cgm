package com.cgm.cgmcodingchallenge.repository;

import com.cgm.cgmcodingchallenge.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitDAO extends JpaRepository<Visit, Long> {

}
