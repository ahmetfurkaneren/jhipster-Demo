package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Fatura;
import com.mycompany.myapp.domain.Sozlesme;
import com.mycompany.myapp.repository.FaturaRepository;

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

/**
 * Integration tests for the {@link FaturaResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FaturaResourceIT {

    private static final Instant DEFAULT_ILK_ODEME_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ILK_ODEME_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SON_ODEME_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SON_ODEME_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ODENEN_TARIH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ODENEN_TARIH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_TOPLAM_TUTAR = 1L;
    private static final Long UPDATED_TOPLAM_TUTAR = 2L;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFaturaMockMvc;

    private Fatura fatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fatura createEntity(EntityManager em) {
        Fatura fatura = new Fatura()
            .ilkOdemeTarihi(DEFAULT_ILK_ODEME_TARIHI)
            .sonOdemeTarihi(DEFAULT_SON_ODEME_TARIHI)
            .odenenTarih(DEFAULT_ODENEN_TARIH)
            .toplamTutar(DEFAULT_TOPLAM_TUTAR);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        fatura.setSozlesme(sozlesme);
        return fatura;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fatura createUpdatedEntity(EntityManager em) {
        Fatura fatura = new Fatura()
            .ilkOdemeTarihi(UPDATED_ILK_ODEME_TARIHI)
            .sonOdemeTarihi(UPDATED_SON_ODEME_TARIHI)
            .odenenTarih(UPDATED_ODENEN_TARIH)
            .toplamTutar(UPDATED_TOPLAM_TUTAR);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createUpdatedEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        fatura.setSozlesme(sozlesme);
        return fatura;
    }

    @BeforeEach
    public void initTest() {
        fatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createFatura() throws Exception {
        int databaseSizeBeforeCreate = faturaRepository.findAll().size();
        // Create the Fatura
        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isCreated());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeCreate + 1);
        Fatura testFatura = faturaList.get(faturaList.size() - 1);
        assertThat(testFatura.getIlkOdemeTarihi()).isEqualTo(DEFAULT_ILK_ODEME_TARIHI);
        assertThat(testFatura.getSonOdemeTarihi()).isEqualTo(DEFAULT_SON_ODEME_TARIHI);
        assertThat(testFatura.getOdenenTarih()).isEqualTo(DEFAULT_ODENEN_TARIH);
        assertThat(testFatura.getToplamTutar()).isEqualTo(DEFAULT_TOPLAM_TUTAR);
    }

    @Test
    @Transactional
    public void createFaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = faturaRepository.findAll().size();

        // Create the Fatura with an existing ID
        fatura.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isBadRequest());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIlkOdemeTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = faturaRepository.findAll().size();
        // set the field null
        fatura.setIlkOdemeTarihi(null);

        // Create the Fatura, which fails.


        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isBadRequest());

        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSonOdemeTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = faturaRepository.findAll().size();
        // set the field null
        fatura.setSonOdemeTarihi(null);

        // Create the Fatura, which fails.


        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isBadRequest());

        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToplamTutarIsRequired() throws Exception {
        int databaseSizeBeforeTest = faturaRepository.findAll().size();
        // set the field null
        fatura.setToplamTutar(null);

        // Create the Fatura, which fails.


        restFaturaMockMvc.perform(post("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isBadRequest());

        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFaturas() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        // Get all the faturaList
        restFaturaMockMvc.perform(get("/api/faturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].ilkOdemeTarihi").value(hasItem(DEFAULT_ILK_ODEME_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].sonOdemeTarihi").value(hasItem(DEFAULT_SON_ODEME_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].odenenTarih").value(hasItem(DEFAULT_ODENEN_TARIH.toString())))
            .andExpect(jsonPath("$.[*].toplamTutar").value(hasItem(DEFAULT_TOPLAM_TUTAR.intValue())));
    }
    
    @Test
    @Transactional
    public void getFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        // Get the fatura
        restFaturaMockMvc.perform(get("/api/faturas/{id}", fatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fatura.getId().intValue()))
            .andExpect(jsonPath("$.ilkOdemeTarihi").value(DEFAULT_ILK_ODEME_TARIHI.toString()))
            .andExpect(jsonPath("$.sonOdemeTarihi").value(DEFAULT_SON_ODEME_TARIHI.toString()))
            .andExpect(jsonPath("$.odenenTarih").value(DEFAULT_ODENEN_TARIH.toString()))
            .andExpect(jsonPath("$.toplamTutar").value(DEFAULT_TOPLAM_TUTAR.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFatura() throws Exception {
        // Get the fatura
        restFaturaMockMvc.perform(get("/api/faturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        int databaseSizeBeforeUpdate = faturaRepository.findAll().size();

        // Update the fatura
        Fatura updatedFatura = faturaRepository.findById(fatura.getId()).get();
        // Disconnect from session so that the updates on updatedFatura are not directly saved in db
        em.detach(updatedFatura);
        updatedFatura
            .ilkOdemeTarihi(UPDATED_ILK_ODEME_TARIHI)
            .sonOdemeTarihi(UPDATED_SON_ODEME_TARIHI)
            .odenenTarih(UPDATED_ODENEN_TARIH)
            .toplamTutar(UPDATED_TOPLAM_TUTAR);

        restFaturaMockMvc.perform(put("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFatura)))
            .andExpect(status().isOk());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeUpdate);
        Fatura testFatura = faturaList.get(faturaList.size() - 1);
        assertThat(testFatura.getIlkOdemeTarihi()).isEqualTo(UPDATED_ILK_ODEME_TARIHI);
        assertThat(testFatura.getSonOdemeTarihi()).isEqualTo(UPDATED_SON_ODEME_TARIHI);
        assertThat(testFatura.getOdenenTarih()).isEqualTo(UPDATED_ODENEN_TARIH);
        assertThat(testFatura.getToplamTutar()).isEqualTo(UPDATED_TOPLAM_TUTAR);
    }

    @Test
    @Transactional
    public void updateNonExistingFatura() throws Exception {
        int databaseSizeBeforeUpdate = faturaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFaturaMockMvc.perform(put("/api/faturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fatura)))
            .andExpect(status().isBadRequest());

        // Validate the Fatura in the database
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFatura() throws Exception {
        // Initialize the database
        faturaRepository.saveAndFlush(fatura);

        int databaseSizeBeforeDelete = faturaRepository.findAll().size();

        // Delete the fatura
        restFaturaMockMvc.perform(delete("/api/faturas/{id}", fatura.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fatura> faturaList = faturaRepository.findAll();
        assertThat(faturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
