package com.cgm.cgmcodingchallenge;

import com.cgm.cgmcodingchallenge.dto.PatientDTO;
import com.cgm.cgmcodingchallenge.repository.PatientDAO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        PatientDTO patientDTO = TestUtils.createPatientDto();

        mvc.perform(post("/cgm/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is(patientDTO.getName())))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())))
                .andExpect(jsonPath("$.socialSecurityNumber", Matchers.is(patientDTO.getSocialSecurityNumber())));

        long deleted = patientDAO.deleteBySocialSecurityNumber(patientDTO.getSocialSecurityNumber());
        Assert.assertEquals(1, deleted);
    }

    @Test
    public void testSave_updatePatient_status200() throws Exception {
        PatientDTO patientDTO = TestUtils.createPatientDto();

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isCreated());

        patientDTO.setSurname("Surname1");

        mvc.perform(put(String.format("/cgm/patients/%s", patientDTO.getSocialSecurityNumber()), patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())));

        long deleted = patientDAO.deleteBySocialSecurityNumber(patientDTO.getSocialSecurityNumber());
        Assert.assertEquals(1, deleted);
    }

    @Test
    public void testSave_fetchPatient_status200() throws Exception {
        PatientDTO patientDTO = TestUtils.createPatientDto();

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isCreated());

        mvc.perform(get(String.format("/cgm/patients/%s", patientDTO.getSocialSecurityNumber()), patientDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is(patientDTO.getName())))
                .andExpect(jsonPath("$.socialSecurityNumber", Matchers.is(patientDTO.getSocialSecurityNumber())))
                .andExpect(jsonPath("$.surname", Matchers.is(patientDTO.getSurname())));

        long deleted = patientDAO.deleteBySocialSecurityNumber(patientDTO.getSocialSecurityNumber());
        Assert.assertEquals(1, deleted);
    }

    @Test
    public void testSave_fetchAll_status200() throws Exception {
        String socialSecurityNumber1 = "SRNNMA91T21L049Z";
        String socialSecurityNumber2 = "SRPNMP91T21L049Z";
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName("Name");
        patientDTO.setSurname("Surname");
        patientDTO.setBirth(Date.valueOf("1991-12-21"));
        patientDTO.setSocialSecurityNumber(socialSecurityNumber1);

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isCreated());

        patientDTO.setName("Name2");
        patientDTO.setSurname("Surname2");
        patientDTO.setBirth(Date.valueOf("1991-12-21"));
        patientDTO.setSocialSecurityNumber(socialSecurityNumber2);

        mvc.perform(post("/cgm/patients", patientDTO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.toJson(patientDTO)))
                .andExpect(status().isCreated());

        mvc.perform(get("/cgm/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

        long deleted = patientDAO.deleteBySocialSecurityNumber(socialSecurityNumber1);
        Assert.assertEquals(1, deleted);
        deleted = patientDAO.deleteBySocialSecurityNumber(socialSecurityNumber2);
        Assert.assertEquals(1, deleted);
    }


}
