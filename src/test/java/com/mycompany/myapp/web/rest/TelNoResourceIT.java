package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.TelNo;
import com.mycompany.myapp.repository.TelNoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TelNoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TelNoResourceIT {

    private static final Integer DEFAULT_NUMARA = 1;
    private static final Integer UPDATED_NUMARA = 2;

    @Autowired
    private TelNoRepository telNoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTelNoMockMvc;

    private TelNo telNo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TelNo createEntity(EntityManager em) {
        TelNo telNo = new TelNo()
            .numara(DEFAULT_NUMARA);
        return telNo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TelNo createUpdatedEntity(EntityManager em) {
        TelNo telNo = new TelNo()
            .numara(UPDATED_NUMARA);
        return telNo;
    }

    @BeforeEach
    public void initTest() {
        telNo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTelNo() throws Exception {
        int databaseSizeBeforeCreate = telNoRepository.findAll().size();
        // Create the TelNo
        restTelNoMockMvc.perform(post("/api/tel-nos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telNo)))
            .andExpect(status().isCreated());

        // Validate the TelNo in the database
        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeCreate + 1);
        TelNo testTelNo = telNoList.get(telNoList.size() - 1);
        assertThat(testTelNo.getNumara()).isEqualTo(DEFAULT_NUMARA);
    }

    @Test
    @Transactional
    public void createTelNoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = telNoRepository.findAll().size();

        // Create the TelNo with an existing ID
        telNo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTelNoMockMvc.perform(post("/api/tel-nos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telNo)))
            .andExpect(status().isBadRequest());

        // Validate the TelNo in the database
        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumaraIsRequired() throws Exception {
        int databaseSizeBeforeTest = telNoRepository.findAll().size();
        // set the field null
        telNo.setNumara(null);

        // Create the TelNo, which fails.


        restTelNoMockMvc.perform(post("/api/tel-nos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telNo)))
            .andExpect(status().isBadRequest());

        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTelNos() throws Exception {
        // Initialize the database
        telNoRepository.saveAndFlush(telNo);

        // Get all the telNoList
        restTelNoMockMvc.perform(get("/api/tel-nos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telNo.getId().intValue())))
            .andExpect(jsonPath("$.[*].numara").value(hasItem(DEFAULT_NUMARA)));
    }
    
    @Test
    @Transactional
    public void getTelNo() throws Exception {
        // Initialize the database
        telNoRepository.saveAndFlush(telNo);

        // Get the telNo
        restTelNoMockMvc.perform(get("/api/tel-nos/{id}", telNo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(telNo.getId().intValue()))
            .andExpect(jsonPath("$.numara").value(DEFAULT_NUMARA));
    }
    @Test
    @Transactional
    public void getNonExistingTelNo() throws Exception {
        // Get the telNo
        restTelNoMockMvc.perform(get("/api/tel-nos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTelNo() throws Exception {
        // Initialize the database
        telNoRepository.saveAndFlush(telNo);

        int databaseSizeBeforeUpdate = telNoRepository.findAll().size();

        // Update the telNo
        TelNo updatedTelNo = telNoRepository.findById(telNo.getId()).get();
        // Disconnect from session so that the updates on updatedTelNo are not directly saved in db
        em.detach(updatedTelNo);
        updatedTelNo
            .numara(UPDATED_NUMARA);

        restTelNoMockMvc.perform(put("/api/tel-nos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTelNo)))
            .andExpect(status().isOk());

        // Validate the TelNo in the database
        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeUpdate);
        TelNo testTelNo = telNoList.get(telNoList.size() - 1);
        assertThat(testTelNo.getNumara()).isEqualTo(UPDATED_NUMARA);
    }

    @Test
    @Transactional
    public void updateNonExistingTelNo() throws Exception {
        int databaseSizeBeforeUpdate = telNoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelNoMockMvc.perform(put("/api/tel-nos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telNo)))
            .andExpect(status().isBadRequest());

        // Validate the TelNo in the database
        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTelNo() throws Exception {
        // Initialize the database
        telNoRepository.saveAndFlush(telNo);

        int databaseSizeBeforeDelete = telNoRepository.findAll().size();

        // Delete the telNo
        restTelNoMockMvc.perform(delete("/api/tel-nos/{id}", telNo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TelNo> telNoList = telNoRepository.findAll();
        assertThat(telNoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
