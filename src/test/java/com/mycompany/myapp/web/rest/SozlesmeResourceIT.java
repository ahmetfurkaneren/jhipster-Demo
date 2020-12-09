package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Sozlesme;
import com.mycompany.myapp.domain.Musteri;
import com.mycompany.myapp.domain.TelNo;
import com.mycompany.myapp.repository.SozlesmeRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Tip;
/**
 * Integration tests for the {@link SozlesmeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SozlesmeResourceIT {

    private static final Tip DEFAULT_TIP = Tip.AnaPaket;
    private static final Tip UPDATED_TIP = Tip.EkPaket;

    private static final Instant DEFAULT_TARIH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TARIH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_BITIS_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BITIS_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SozlesmeRepository sozlesmeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSozlesmeMockMvc;

    private Sozlesme sozlesme;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sozlesme createEntity(EntityManager em) {
        Sozlesme sozlesme = new Sozlesme()
            .tip(DEFAULT_TIP)
            .tarih(DEFAULT_TARIH)
            .bitisTarihi(DEFAULT_BITIS_TARIHI);
        // Add required entity
        Musteri musteri;
        if (TestUtil.findAll(em, Musteri.class).isEmpty()) {
            musteri = MusteriResourceIT.createEntity(em);
            em.persist(musteri);
            em.flush();
        } else {
            musteri = TestUtil.findAll(em, Musteri.class).get(0);
        }
        sozlesme.setMusteri(musteri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        sozlesme.setTelNo(telNo);
        return sozlesme;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sozlesme createUpdatedEntity(EntityManager em) {
        Sozlesme sozlesme = new Sozlesme()
            .tip(UPDATED_TIP)
            .tarih(UPDATED_TARIH)
            .bitisTarihi(UPDATED_BITIS_TARIHI);
        // Add required entity
        Musteri musteri;
        if (TestUtil.findAll(em, Musteri.class).isEmpty()) {
            musteri = MusteriResourceIT.createUpdatedEntity(em);
            em.persist(musteri);
            em.flush();
        } else {
            musteri = TestUtil.findAll(em, Musteri.class).get(0);
        }
        sozlesme.setMusteri(musteri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createUpdatedEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        sozlesme.setTelNo(telNo);
        return sozlesme;
    }

    @BeforeEach
    public void initTest() {
        sozlesme = createEntity(em);
    }

    @Test
    @Transactional
    public void createSozlesme() throws Exception {
        int databaseSizeBeforeCreate = sozlesmeRepository.findAll().size();
        // Create the Sozlesme
        restSozlesmeMockMvc.perform(post("/api/sozlesmes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesme)))
            .andExpect(status().isCreated());

        // Validate the Sozlesme in the database
        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeCreate + 1);
        Sozlesme testSozlesme = sozlesmeList.get(sozlesmeList.size() - 1);
        assertThat(testSozlesme.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testSozlesme.getTarih()).isEqualTo(DEFAULT_TARIH);
        assertThat(testSozlesme.getBitisTarihi()).isEqualTo(DEFAULT_BITIS_TARIHI);
    }

    @Test
    @Transactional
    public void createSozlesmeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sozlesmeRepository.findAll().size();

        // Create the Sozlesme with an existing ID
        sozlesme.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSozlesmeMockMvc.perform(post("/api/sozlesmes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesme)))
            .andExpect(status().isBadRequest());

        // Validate the Sozlesme in the database
        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTarihIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeRepository.findAll().size();
        // set the field null
        sozlesme.setTarih(null);

        // Create the Sozlesme, which fails.


        restSozlesmeMockMvc.perform(post("/api/sozlesmes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesme)))
            .andExpect(status().isBadRequest());

        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSozlesmes() throws Exception {
        // Initialize the database
        sozlesmeRepository.saveAndFlush(sozlesme);

        // Get all the sozlesmeList
        restSozlesmeMockMvc.perform(get("/api/sozlesmes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sozlesme.getId().intValue())))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.toString())))
            .andExpect(jsonPath("$.[*].tarih").value(hasItem(DEFAULT_TARIH.toString())))
            .andExpect(jsonPath("$.[*].bitisTarihi").value(hasItem(DEFAULT_BITIS_TARIHI.toString())));
    }
    
    @Test
    @Transactional
    public void getSozlesme() throws Exception {
        // Initialize the database
        sozlesmeRepository.saveAndFlush(sozlesme);

        // Get the sozlesme
        restSozlesmeMockMvc.perform(get("/api/sozlesmes/{id}", sozlesme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sozlesme.getId().intValue()))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.toString()))
            .andExpect(jsonPath("$.tarih").value(DEFAULT_TARIH.toString()))
            .andExpect(jsonPath("$.bitisTarihi").value(DEFAULT_BITIS_TARIHI.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSozlesme() throws Exception {
        // Get the sozlesme
        restSozlesmeMockMvc.perform(get("/api/sozlesmes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSozlesme() throws Exception {
        // Initialize the database
        sozlesmeRepository.saveAndFlush(sozlesme);

        int databaseSizeBeforeUpdate = sozlesmeRepository.findAll().size();

        // Update the sozlesme
        Sozlesme updatedSozlesme = sozlesmeRepository.findById(sozlesme.getId()).get();
        // Disconnect from session so that the updates on updatedSozlesme are not directly saved in db
        em.detach(updatedSozlesme);
        updatedSozlesme
            .tip(UPDATED_TIP)
            .tarih(UPDATED_TARIH)
            .bitisTarihi(UPDATED_BITIS_TARIHI);

        restSozlesmeMockMvc.perform(put("/api/sozlesmes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSozlesme)))
            .andExpect(status().isOk());

        // Validate the Sozlesme in the database
        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeUpdate);
        Sozlesme testSozlesme = sozlesmeList.get(sozlesmeList.size() - 1);
        assertThat(testSozlesme.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testSozlesme.getTarih()).isEqualTo(UPDATED_TARIH);
        assertThat(testSozlesme.getBitisTarihi()).isEqualTo(UPDATED_BITIS_TARIHI);
    }

    @Test
    @Transactional
    public void updateNonExistingSozlesme() throws Exception {
        int databaseSizeBeforeUpdate = sozlesmeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSozlesmeMockMvc.perform(put("/api/sozlesmes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesme)))
            .andExpect(status().isBadRequest());

        // Validate the Sozlesme in the database
        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSozlesme() throws Exception {
        // Initialize the database
        sozlesmeRepository.saveAndFlush(sozlesme);

        int databaseSizeBeforeDelete = sozlesmeRepository.findAll().size();

        // Delete the sozlesme
        restSozlesmeMockMvc.perform(delete("/api/sozlesmes/{id}", sozlesme.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sozlesme> sozlesmeList = sozlesmeRepository.findAll();
        assertThat(sozlesmeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
