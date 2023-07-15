package com.cgm.cgmcodingchallenge.repository;

import com.cgm.cgmcodingchallenge.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface VisitDAO extends JpaRepository<Visit, Long> {
    Visit findByVisitIdAndPatientSocialSecurityNumber(Long visitId, String socialSecurityNumber);

    @Query("SELECT COUNT(v) FROM Visit v WHERE ?1 BETWEEN v.startDate AND v.endDate OR ?2 BETWEEN v.startDate AND v.endDate")
    Long findOverlappingDate(Timestamp startDate, Timestamp endDate);

    @Query("SELECT COUNT(v) FROM Visit v WHERE ?1 BETWEEN v.startDate AND v.endDate OR ?2 BETWEEN v.startDate AND v.endDate AND v.visitId NOT IN(?3)")
    Long findOverlappingDateExcludeSomeVisits(Timestamp startDate, Timestamp endDate, List<Long> visitIds);
}
