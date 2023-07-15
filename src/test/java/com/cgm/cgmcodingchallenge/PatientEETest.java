package com.cgm.cgmcodingchallenge;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CgmcodingchallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
public class PatientEETest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientDAO patientDAO;

    @Test
    public void testSave_newPatient_status201() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Name");
        patientDTO.setSurname("Surname");
        patientDTO.setBirth(Date.valueOf("1991-12-21"));
        patientDTO.setSocialSecurityNumber("SRNNMA91T21L049Z");

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(patientDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is(patientDTO.getName())))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())))
                .andExpect(jsonPath("$.socialSecurityNumber", Matchers.is(patientDTO.getSocialSecurityNumber())));

        long deleted = patientDAO.deleteBySocialSecurityNumber(patientDTO.getSocialSecurityNumber());
        Assert.assertEquals(1, deleted);
    }

    @Test
    public void testSave_updatePatient_status201() throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Name");
        patientDTO.setSurname("Surname");
        patientDTO.setBirth(Date.valueOf("1991-12-21"));
        patientDTO.setSocialSecurityNumber("SRNNMA91T21L049Z");

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(patientDTO)))
                .andExpect(status().isCreated());

        patientDTO.setSurname("Surname1");

        mvc.perform(put(String.format("/cgm/patients/%s", patientDTO.getSocialSecurityNumber()), patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(patientDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())));

        long deleted = patientDAO.deleteBySocialSecurityNumber(patientDTO.getSocialSecurityNumber());
        Assert.assertEquals(1, deleted);
    }

    private static String toJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
