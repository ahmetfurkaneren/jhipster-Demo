package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Musteri;
import com.mycompany.myapp.repository.MusteriRepository;

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

import com.mycompany.myapp.domain.enumeration.MusteriTipi;
/**
 * Integration tests for the {@link MusteriResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MusteriResourceIT {

    private static final String DEFAULT_AD = "AAAAAAAAAA";
    private static final String UPDATED_AD = "BBBBBBBBBB";

    private static final String DEFAULT_SOYAD = "AAAAAAAAAA";
    private static final String UPDATED_SOYAD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAROLA = "AAAAAAAAAA";
    private static final String UPDATED_PAROLA = "BBBBBBBBBB";

    private static final String DEFAULT_T_C = "AAAAAAAAAAA";
    private static final String UPDATED_T_C = "BBBBBBBBBBB";

    private static final MusteriTipi DEFAULT_MUSTERI_TIPI = MusteriTipi.Bireysel;
    private static final MusteriTipi UPDATED_MUSTERI_TIPI = MusteriTipi.Kurumsal;

    private static final Instant DEFAULT_DOGUM_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DOGUM_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MusteriRepository musteriRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMusteriMockMvc;

    private Musteri musteri;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Musteri createEntity(EntityManager em) {
        Musteri musteri = new Musteri()
            .ad(DEFAULT_AD)
            .soyad(DEFAULT_SOYAD)
            .email(DEFAULT_EMAIL)
            .parola(DEFAULT_PAROLA)
            .tC(DEFAULT_T_C)
            .musteriTipi(DEFAULT_MUSTERI_TIPI)
            .dogumTarihi(DEFAULT_DOGUM_TARIHI);
        return musteri;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Musteri createUpdatedEntity(EntityManager em) {
        Musteri musteri = new Musteri()
            .ad(UPDATED_AD)
            .soyad(UPDATED_SOYAD)
            .email(UPDATED_EMAIL)
            .parola(UPDATED_PAROLA)
            .tC(UPDATED_T_C)
            .musteriTipi(UPDATED_MUSTERI_TIPI)
            .dogumTarihi(UPDATED_DOGUM_TARIHI);
        return musteri;
    }

    @BeforeEach
    public void initTest() {
        musteri = createEntity(em);
    }

    @Test
    @Transactional
    public void createMusteri() throws Exception {
        int databaseSizeBeforeCreate = musteriRepository.findAll().size();
        // Create the Musteri
        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isCreated());

        // Validate the Musteri in the database
        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeCreate + 1);
        Musteri testMusteri = musteriList.get(musteriList.size() - 1);
        assertThat(testMusteri.getAd()).isEqualTo(DEFAULT_AD);
        assertThat(testMusteri.getSoyad()).isEqualTo(DEFAULT_SOYAD);
        assertThat(testMusteri.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMusteri.getParola()).isEqualTo(DEFAULT_PAROLA);
        assertThat(testMusteri.gettC()).isEqualTo(DEFAULT_T_C);
        assertThat(testMusteri.getMusteriTipi()).isEqualTo(DEFAULT_MUSTERI_TIPI);
        assertThat(testMusteri.getDogumTarihi()).isEqualTo(DEFAULT_DOGUM_TARIHI);
    }

    @Test
    @Transactional
    public void createMusteriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = musteriRepository.findAll().size();

        // Create the Musteri with an existing ID
        musteri.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        // Validate the Musteri in the database
        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAdIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.setAd(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSoyadIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.setSoyad(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.setEmail(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParolaIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.setParola(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checktCIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.settC(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDogumTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = musteriRepository.findAll().size();
        // set the field null
        musteri.setDogumTarihi(null);

        // Create the Musteri, which fails.


        restMusteriMockMvc.perform(post("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMusteris() throws Exception {
        // Initialize the database
        musteriRepository.saveAndFlush(musteri);

        // Get all the musteriList
        restMusteriMockMvc.perform(get("/api/musteris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(musteri.getId().intValue())))
            .andExpect(jsonPath("$.[*].ad").value(hasItem(DEFAULT_AD)))
            .andExpect(jsonPath("$.[*].soyad").value(hasItem(DEFAULT_SOYAD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].parola").value(hasItem(DEFAULT_PAROLA)))
            .andExpect(jsonPath("$.[*].tC").value(hasItem(DEFAULT_T_C)))
            .andExpect(jsonPath("$.[*].musteriTipi").value(hasItem(DEFAULT_MUSTERI_TIPI.toString())))
            .andExpect(jsonPath("$.[*].dogumTarihi").value(hasItem(DEFAULT_DOGUM_TARIHI.toString())));
    }
    
    @Test
    @Transactional
    public void getMusteri() throws Exception {
        // Initialize the database
        musteriRepository.saveAndFlush(musteri);

        // Get the musteri
        restMusteriMockMvc.perform(get("/api/musteris/{id}", musteri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(musteri.getId().intValue()))
            .andExpect(jsonPath("$.ad").value(DEFAULT_AD))
            .andExpect(jsonPath("$.soyad").value(DEFAULT_SOYAD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.parola").value(DEFAULT_PAROLA))
            .andExpect(jsonPath("$.tC").value(DEFAULT_T_C))
            .andExpect(jsonPath("$.musteriTipi").value(DEFAULT_MUSTERI_TIPI.toString()))
            .andExpect(jsonPath("$.dogumTarihi").value(DEFAULT_DOGUM_TARIHI.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMusteri() throws Exception {
        // Get the musteri
        restMusteriMockMvc.perform(get("/api/musteris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMusteri() throws Exception {
        // Initialize the database
        musteriRepository.saveAndFlush(musteri);

        int databaseSizeBeforeUpdate = musteriRepository.findAll().size();

        // Update the musteri
        Musteri updatedMusteri = musteriRepository.findById(musteri.getId()).get();
        // Disconnect from session so that the updates on updatedMusteri are not directly saved in db
        em.detach(updatedMusteri);
        updatedMusteri
            .ad(UPDATED_AD)
            .soyad(UPDATED_SOYAD)
            .email(UPDATED_EMAIL)
            .parola(UPDATED_PAROLA)
            .tC(UPDATED_T_C)
            .musteriTipi(UPDATED_MUSTERI_TIPI)
            .dogumTarihi(UPDATED_DOGUM_TARIHI);

        restMusteriMockMvc.perform(put("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMusteri)))
            .andExpect(status().isOk());

        // Validate the Musteri in the database
        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeUpdate);
        Musteri testMusteri = musteriList.get(musteriList.size() - 1);
        assertThat(testMusteri.getAd()).isEqualTo(UPDATED_AD);
        assertThat(testMusteri.getSoyad()).isEqualTo(UPDATED_SOYAD);
        assertThat(testMusteri.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMusteri.getParola()).isEqualTo(UPDATED_PAROLA);
        assertThat(testMusteri.gettC()).isEqualTo(UPDATED_T_C);
        assertThat(testMusteri.getMusteriTipi()).isEqualTo(UPDATED_MUSTERI_TIPI);
        assertThat(testMusteri.getDogumTarihi()).isEqualTo(UPDATED_DOGUM_TARIHI);
    }

    @Test
    @Transactional
    public void updateNonExistingMusteri() throws Exception {
        int databaseSizeBeforeUpdate = musteriRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMusteriMockMvc.perform(put("/api/musteris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(musteri)))
            .andExpect(status().isBadRequest());

        // Validate the Musteri in the database
        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMusteri() throws Exception {
        // Initialize the database
        musteriRepository.saveAndFlush(musteri);

        int databaseSizeBeforeDelete = musteriRepository.findAll().size();

        // Delete the musteri
        restMusteriMockMvc.perform(delete("/api/musteris/{id}", musteri.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Musteri> musteriList = musteriRepository.findAll();
        assertThat(musteriList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
