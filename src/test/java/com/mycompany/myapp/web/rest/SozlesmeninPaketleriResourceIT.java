package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.SozlesmeninPaketleri;
import com.mycompany.myapp.domain.Sozlesme;
import com.mycompany.myapp.domain.Paketler;
import com.mycompany.myapp.repository.SozlesmeninPaketleriRepository;

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
 * Integration tests for the {@link SozlesmeninPaketleriResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SozlesmeninPaketleriResourceIT {

    private static final Integer DEFAULT_FIYAT = 1;
    private static final Integer UPDATED_FIYAT = 2;

    private static final Instant DEFAULT_BASLANGIC_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BASLANGIC_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_BITIS_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BITIS_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_KALAN_DAKIKA = 1;
    private static final Integer UPDATED_KALAN_DAKIKA = 2;

    private static final Integer DEFAULT_KALAN_SMS = 1;
    private static final Integer UPDATED_KALAN_SMS = 2;

    private static final Integer DEFAULT_KALAN_INTERNET = 1;
    private static final Integer UPDATED_KALAN_INTERNET = 2;

    @Autowired
    private SozlesmeninPaketleriRepository sozlesmeninPaketleriRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSozlesmeninPaketleriMockMvc;

    private SozlesmeninPaketleri sozlesmeninPaketleri;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SozlesmeninPaketleri createEntity(EntityManager em) {
        SozlesmeninPaketleri sozlesmeninPaketleri = new SozlesmeninPaketleri()
            .fiyat(DEFAULT_FIYAT)
            .baslangicTarihi(DEFAULT_BASLANGIC_TARIHI)
            .bitisTarihi(DEFAULT_BITIS_TARIHI)
            .kalanDakika(DEFAULT_KALAN_DAKIKA)
            .kalanSms(DEFAULT_KALAN_SMS)
            .kalanInternet(DEFAULT_KALAN_INTERNET);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        sozlesmeninPaketleri.setSozlesme(sozlesme);
        // Add required entity
        Paketler paketler;
        if (TestUtil.findAll(em, Paketler.class).isEmpty()) {
            paketler = PaketlerResourceIT.createEntity(em);
            em.persist(paketler);
            em.flush();
        } else {
            paketler = TestUtil.findAll(em, Paketler.class).get(0);
        }
        sozlesmeninPaketleri.setPaketler(paketler);
        return sozlesmeninPaketleri;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SozlesmeninPaketleri createUpdatedEntity(EntityManager em) {
        SozlesmeninPaketleri sozlesmeninPaketleri = new SozlesmeninPaketleri()
            .fiyat(UPDATED_FIYAT)
            .baslangicTarihi(UPDATED_BASLANGIC_TARIHI)
            .bitisTarihi(UPDATED_BITIS_TARIHI)
            .kalanDakika(UPDATED_KALAN_DAKIKA)
            .kalanSms(UPDATED_KALAN_SMS)
            .kalanInternet(UPDATED_KALAN_INTERNET);
        // Add required entity
        Sozlesme sozlesme;
        if (TestUtil.findAll(em, Sozlesme.class).isEmpty()) {
            sozlesme = SozlesmeResourceIT.createUpdatedEntity(em);
            em.persist(sozlesme);
            em.flush();
        } else {
            sozlesme = TestUtil.findAll(em, Sozlesme.class).get(0);
        }
        sozlesmeninPaketleri.setSozlesme(sozlesme);
        // Add required entity
        Paketler paketler;
        if (TestUtil.findAll(em, Paketler.class).isEmpty()) {
            paketler = PaketlerResourceIT.createUpdatedEntity(em);
            em.persist(paketler);
            em.flush();
        } else {
            paketler = TestUtil.findAll(em, Paketler.class).get(0);
        }
        sozlesmeninPaketleri.setPaketler(paketler);
        return sozlesmeninPaketleri;
    }

    @BeforeEach
    public void initTest() {
        sozlesmeninPaketleri = createEntity(em);
    }

    @Test
    @Transactional
    public void createSozlesmeninPaketleri() throws Exception {
        int databaseSizeBeforeCreate = sozlesmeninPaketleriRepository.findAll().size();
        // Create the SozlesmeninPaketleri
        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isCreated());

        // Validate the SozlesmeninPaketleri in the database
        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeCreate + 1);
        SozlesmeninPaketleri testSozlesmeninPaketleri = sozlesmeninPaketleriList.get(sozlesmeninPaketleriList.size() - 1);
        assertThat(testSozlesmeninPaketleri.getFiyat()).isEqualTo(DEFAULT_FIYAT);
        assertThat(testSozlesmeninPaketleri.getBaslangicTarihi()).isEqualTo(DEFAULT_BASLANGIC_TARIHI);
        assertThat(testSozlesmeninPaketleri.getBitisTarihi()).isEqualTo(DEFAULT_BITIS_TARIHI);
        assertThat(testSozlesmeninPaketleri.getKalanDakika()).isEqualTo(DEFAULT_KALAN_DAKIKA);
        assertThat(testSozlesmeninPaketleri.getKalanSms()).isEqualTo(DEFAULT_KALAN_SMS);
        assertThat(testSozlesmeninPaketleri.getKalanInternet()).isEqualTo(DEFAULT_KALAN_INTERNET);
    }

    @Test
    @Transactional
    public void createSozlesmeninPaketleriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sozlesmeninPaketleriRepository.findAll().size();

        // Create the SozlesmeninPaketleri with an existing ID
        sozlesmeninPaketleri.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        // Validate the SozlesmeninPaketleri in the database
        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFiyatIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setFiyat(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaslangicTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setBaslangicTarihi(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBitisTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setBitisTarihi(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKalanDakikaIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setKalanDakika(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKalanSmsIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setKalanSms(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKalanInternetIsRequired() throws Exception {
        int databaseSizeBeforeTest = sozlesmeninPaketleriRepository.findAll().size();
        // set the field null
        sozlesmeninPaketleri.setKalanInternet(null);

        // Create the SozlesmeninPaketleri, which fails.


        restSozlesmeninPaketleriMockMvc.perform(post("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSozlesmeninPaketleris() throws Exception {
        // Initialize the database
        sozlesmeninPaketleriRepository.saveAndFlush(sozlesmeninPaketleri);

        // Get all the sozlesmeninPaketleriList
        restSozlesmeninPaketleriMockMvc.perform(get("/api/sozlesmenin-paketleris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sozlesmeninPaketleri.getId().intValue())))
            .andExpect(jsonPath("$.[*].fiyat").value(hasItem(DEFAULT_FIYAT)))
            .andExpect(jsonPath("$.[*].baslangicTarihi").value(hasItem(DEFAULT_BASLANGIC_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].bitisTarihi").value(hasItem(DEFAULT_BITIS_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].kalanDakika").value(hasItem(DEFAULT_KALAN_DAKIKA)))
            .andExpect(jsonPath("$.[*].kalanSms").value(hasItem(DEFAULT_KALAN_SMS)))
            .andExpect(jsonPath("$.[*].kalanInternet").value(hasItem(DEFAULT_KALAN_INTERNET)));
    }
    
    @Test
    @Transactional
    public void getSozlesmeninPaketleri() throws Exception {
        // Initialize the database
        sozlesmeninPaketleriRepository.saveAndFlush(sozlesmeninPaketleri);

        // Get the sozlesmeninPaketleri
        restSozlesmeninPaketleriMockMvc.perform(get("/api/sozlesmenin-paketleris/{id}", sozlesmeninPaketleri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sozlesmeninPaketleri.getId().intValue()))
            .andExpect(jsonPath("$.fiyat").value(DEFAULT_FIYAT))
            .andExpect(jsonPath("$.baslangicTarihi").value(DEFAULT_BASLANGIC_TARIHI.toString()))
            .andExpect(jsonPath("$.bitisTarihi").value(DEFAULT_BITIS_TARIHI.toString()))
            .andExpect(jsonPath("$.kalanDakika").value(DEFAULT_KALAN_DAKIKA))
            .andExpect(jsonPath("$.kalanSms").value(DEFAULT_KALAN_SMS))
            .andExpect(jsonPath("$.kalanInternet").value(DEFAULT_KALAN_INTERNET));
    }
    @Test
    @Transactional
    public void getNonExistingSozlesmeninPaketleri() throws Exception {
        // Get the sozlesmeninPaketleri
        restSozlesmeninPaketleriMockMvc.perform(get("/api/sozlesmenin-paketleris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSozlesmeninPaketleri() throws Exception {
        // Initialize the database
        sozlesmeninPaketleriRepository.saveAndFlush(sozlesmeninPaketleri);

        int databaseSizeBeforeUpdate = sozlesmeninPaketleriRepository.findAll().size();

        // Update the sozlesmeninPaketleri
        SozlesmeninPaketleri updatedSozlesmeninPaketleri = sozlesmeninPaketleriRepository.findById(sozlesmeninPaketleri.getId()).get();
        // Disconnect from session so that the updates on updatedSozlesmeninPaketleri are not directly saved in db
        em.detach(updatedSozlesmeninPaketleri);
        updatedSozlesmeninPaketleri
            .fiyat(UPDATED_FIYAT)
            .baslangicTarihi(UPDATED_BASLANGIC_TARIHI)
            .bitisTarihi(UPDATED_BITIS_TARIHI)
            .kalanDakika(UPDATED_KALAN_DAKIKA)
            .kalanSms(UPDATED_KALAN_SMS)
            .kalanInternet(UPDATED_KALAN_INTERNET);

        restSozlesmeninPaketleriMockMvc.perform(put("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSozlesmeninPaketleri)))
            .andExpect(status().isOk());

        // Validate the SozlesmeninPaketleri in the database
        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeUpdate);
        SozlesmeninPaketleri testSozlesmeninPaketleri = sozlesmeninPaketleriList.get(sozlesmeninPaketleriList.size() - 1);
        assertThat(testSozlesmeninPaketleri.getFiyat()).isEqualTo(UPDATED_FIYAT);
        assertThat(testSozlesmeninPaketleri.getBaslangicTarihi()).isEqualTo(UPDATED_BASLANGIC_TARIHI);
        assertThat(testSozlesmeninPaketleri.getBitisTarihi()).isEqualTo(UPDATED_BITIS_TARIHI);
        assertThat(testSozlesmeninPaketleri.getKalanDakika()).isEqualTo(UPDATED_KALAN_DAKIKA);
        assertThat(testSozlesmeninPaketleri.getKalanSms()).isEqualTo(UPDATED_KALAN_SMS);
        assertThat(testSozlesmeninPaketleri.getKalanInternet()).isEqualTo(UPDATED_KALAN_INTERNET);
    }

    @Test
    @Transactional
    public void updateNonExistingSozlesmeninPaketleri() throws Exception {
        int databaseSizeBeforeUpdate = sozlesmeninPaketleriRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSozlesmeninPaketleriMockMvc.perform(put("/api/sozlesmenin-paketleris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sozlesmeninPaketleri)))
            .andExpect(status().isBadRequest());

        // Validate the SozlesmeninPaketleri in the database
        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSozlesmeninPaketleri() throws Exception {
        // Initialize the database
        sozlesmeninPaketleriRepository.saveAndFlush(sozlesmeninPaketleri);

        int databaseSizeBeforeDelete = sozlesmeninPaketleriRepository.findAll().size();

        // Delete the sozlesmeninPaketleri
        restSozlesmeninPaketleriMockMvc.perform(delete("/api/sozlesmenin-paketleris/{id}", sozlesmeninPaketleri.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SozlesmeninPaketleri> sozlesmeninPaketleriList = sozlesmeninPaketleriRepository.findAll();
        assertThat(sozlesmeninPaketleriList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
