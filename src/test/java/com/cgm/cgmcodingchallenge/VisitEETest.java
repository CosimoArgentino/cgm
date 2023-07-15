package com.cgm.cgmcodingchallenge;

import com.cgm.cgmcodingchallenge.dto.VisitDTO;
import com.cgm.cgmcodingchallenge.entities.Patient;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.cgm.cgmcodingchallenge.repository.VisitDAO;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CgmcodingchallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
public class VisitEETest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private VisitDAO visitDao;

    private final String socialSecurityNumber = "SRNNMA91T21L049Z";

    @Test
    public void testSave_newVisit_status201() throws Exception {
        Patient patient = TestUtils.createPatient();

        patientDAO.save(patient);

        VisitDTO visitDTO = TestUtils.createVisitDto();
        mvc.perform(post("/cgm/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(visitDTO)))
                .andExpect(jsonPath("$.socialSecurityNumber", Matchers.is(patient.getSocialSecurityNumber())))
                .andExpect(jsonPath("$.familyHistory", Matchers.is(visitDTO.getFamilyHistory())))
                .andExpect(status().isCreated());
    }
}
