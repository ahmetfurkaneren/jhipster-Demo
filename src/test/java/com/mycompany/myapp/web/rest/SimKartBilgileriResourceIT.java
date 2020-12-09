package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.SimKartBilgileri;
import com.mycompany.myapp.domain.Sozlesme;
import com.mycompany.myapp.repository.SimKartBilgileriRepository;

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
 * Integration tests for the {@link SimKartBilgileriResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SimKartBilgileriResourceIT {

    private static final Integer DEFAULT_PIN_NO = 4;
    private static final Integer UPDATED_PIN_NO = 3;

    private static final Integer DEFAULT_PUK_NO = 8;
    private static final Integer UPDATED_PUK_NO = 7;

    private static final String DEFAULT_BARKOD = "AAAAAAAAAA";
    private static final String UPDATED_BARKOD = "BBBBBBBBBB";

    private static final Integer DEFAULT_BIT_MIKTARI = 1;
    private static final Integer UPDATED_BIT_MIKTARI = 2;

    @Autowired
    private SimKartBilgileriRepository simKartBilgileriRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSimKartBilgileriMockMvc;

    private SimKartBilgileri simKartBilgileri;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SimKartBilgileri createEntity(EntityManager em) {
        SimKartBilgileri simKartBilgileri = new SimKartBilgileri()
            .pinNo(DEFAULT_PIN_NO)
            .pukNo(DEFAULT_PUK_NO)
            .barkod(DEFAULT_BARKOD)
            .bitMiktari(DEFAULT_BIT_MIKTARI);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        simKartBilgileri.setSozlesme(sozlesme);
        return simKartBilgileri;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SimKartBilgileri createUpdatedEntity(EntityManager em) {
        SimKartBilgileri simKartBilgileri = new SimKartBilgileri()
            .pinNo(UPDATED_PIN_NO)
            .pukNo(UPDATED_PUK_NO)
            .barkod(UPDATED_BARKOD)
            .bitMiktari(UPDATED_BIT_MIKTARI);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createUpdatedEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        simKartBilgileri.setSozlesme(sozlesme);
        return simKartBilgileri;
    }

    @BeforeEach
    public void initTest() {
        simKartBilgileri = createEntity(em);
    }

    @Test
    @Transactional
    public void createSimKartBilgileri() throws Exception {
        int databaseSizeBeforeCreate = simKartBilgileriRepository.findAll().size();
        // Create the SimKartBilgileri
        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isCreated());

        // Validate the SimKartBilgileri in the database
        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeCreate + 1);
        SimKartBilgileri testSimKartBilgileri = simKartBilgileriList.get(simKartBilgileriList.size() - 1);
        assertThat(testSimKartBilgileri.getPinNo()).isEqualTo(DEFAULT_PIN_NO);
        assertThat(testSimKartBilgileri.getPukNo()).isEqualTo(DEFAULT_PUK_NO);
        assertThat(testSimKartBilgileri.getBarkod()).isEqualTo(DEFAULT_BARKOD);
        assertThat(testSimKartBilgileri.getBitMiktari()).isEqualTo(DEFAULT_BIT_MIKTARI);
    }

    @Test
    @Transactional
    public void createSimKartBilgileriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = simKartBilgileriRepository.findAll().size();

        // Create the SimKartBilgileri with an existing ID
        simKartBilgileri.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        // Validate the SimKartBilgileri in the database
        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPinNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = simKartBilgileriRepository.findAll().size();
        // set the field null
        simKartBilgileri.setPinNo(null);

        // Create the SimKartBilgileri, which fails.


        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPukNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = simKartBilgileriRepository.findAll().size();
        // set the field null
        simKartBilgileri.setPukNo(null);

        // Create the SimKartBilgileri, which fails.


        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBarkodIsRequired() throws Exception {
        int databaseSizeBeforeTest = simKartBilgileriRepository.findAll().size();
        // set the field null
        simKartBilgileri.setBarkod(null);

        // Create the SimKartBilgileri, which fails.


        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBitMiktariIsRequired() throws Exception {
        int databaseSizeBeforeTest = simKartBilgileriRepository.findAll().size();
        // set the field null
        simKartBilgileri.setBitMiktari(null);

        // Create the SimKartBilgileri, which fails.


        restSimKartBilgileriMockMvc.perform(post("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSimKartBilgileris() throws Exception {
        // Initialize the database
        simKartBilgileriRepository.saveAndFlush(simKartBilgileri);

        // Get all the simKartBilgileriList
        restSimKartBilgileriMockMvc.perform(get("/api/sim-kart-bilgileris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(simKartBilgileri.getId().intValue())))
            .andExpect(jsonPath("$.[*].pinNo").value(hasItem(DEFAULT_PIN_NO)))
            .andExpect(jsonPath("$.[*].pukNo").value(hasItem(DEFAULT_PUK_NO)))
            .andExpect(jsonPath("$.[*].barkod").value(hasItem(DEFAULT_BARKOD)))
            .andExpect(jsonPath("$.[*].bitMiktari").value(hasItem(DEFAULT_BIT_MIKTARI)));
    }
    
    @Test
    @Transactional
    public void getSimKartBilgileri() throws Exception {
        // Initialize the database
        simKartBilgileriRepository.saveAndFlush(simKartBilgileri);

        // Get the simKartBilgileri
        restSimKartBilgileriMockMvc.perform(get("/api/sim-kart-bilgileris/{id}", simKartBilgileri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(simKartBilgileri.getId().intValue()))
            .andExpect(jsonPath("$.pinNo").value(DEFAULT_PIN_NO))
            .andExpect(jsonPath("$.pukNo").value(DEFAULT_PUK_NO))
            .andExpect(jsonPath("$.barkod").value(DEFAULT_BARKOD))
            .andExpect(jsonPath("$.bitMiktari").value(DEFAULT_BIT_MIKTARI));
    }
    @Test
    @Transactional
    public void getNonExistingSimKartBilgileri() throws Exception {
        // Get the simKartBilgileri
        restSimKartBilgileriMockMvc.perform(get("/api/sim-kart-bilgileris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSimKartBilgileri() throws Exception {
        // Initialize the database
        simKartBilgileriRepository.saveAndFlush(simKartBilgileri);

        int databaseSizeBeforeUpdate = simKartBilgileriRepository.findAll().size();

        // Update the simKartBilgileri
        SimKartBilgileri updatedSimKartBilgileri = simKartBilgileriRepository.findById(simKartBilgileri.getId()).get();
        // Disconnect from session so that the updates on updatedSimKartBilgileri are not directly saved in db
        em.detach(updatedSimKartBilgileri);
        updatedSimKartBilgileri
            .pinNo(UPDATED_PIN_NO)
            .pukNo(UPDATED_PUK_NO)
            .barkod(UPDATED_BARKOD)
            .bitMiktari(UPDATED_BIT_MIKTARI);

        restSimKartBilgileriMockMvc.perform(put("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSimKartBilgileri)))
            .andExpect(status().isOk());

        // Validate the SimKartBilgileri in the database
        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeUpdate);
        SimKartBilgileri testSimKartBilgileri = simKartBilgileriList.get(simKartBilgileriList.size() - 1);
        assertThat(testSimKartBilgileri.getPinNo()).isEqualTo(UPDATED_PIN_NO);
        assertThat(testSimKartBilgileri.getPukNo()).isEqualTo(UPDATED_PUK_NO);
        assertThat(testSimKartBilgileri.getBarkod()).isEqualTo(UPDATED_BARKOD);
        assertThat(testSimKartBilgileri.getBitMiktari()).isEqualTo(UPDATED_BIT_MIKTARI);
    }

    @Test
    @Transactional
    public void updateNonExistingSimKartBilgileri() throws Exception {
        int databaseSizeBeforeUpdate = simKartBilgileriRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSimKartBilgileriMockMvc.perform(put("/api/sim-kart-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(simKartBilgileri)))
            .andExpect(status().isBadRequest());

        // Validate the SimKartBilgileri in the database
        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSimKartBilgileri() throws Exception {
        // Initialize the database
        simKartBilgileriRepository.saveAndFlush(simKartBilgileri);

        int databaseSizeBeforeDelete = simKartBilgileriRepository.findAll().size();

        // Delete the simKartBilgileri
        restSimKartBilgileriMockMvc.perform(delete("/api/sim-kart-bilgileris/{id}", simKartBilgileri.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SimKartBilgileri> simKartBilgileriList = simKartBilgileriRepository.findAll();
        assertThat(simKartBilgileriList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
