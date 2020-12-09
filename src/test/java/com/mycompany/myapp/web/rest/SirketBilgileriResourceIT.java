package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.SirketBilgileri;
import com.mycompany.myapp.domain.Musteri;
import com.mycompany.myapp.repository.SirketBilgileriRepository;

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
 * Integration tests for the {@link SirketBilgileriResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SirketBilgileriResourceIT {

    private static final String DEFAULT_SIRKET_ADI = "AAAAAAAAAA";
    private static final String UPDATED_SIRKET_ADI = "BBBBBBBBBB";

    private static final String DEFAULT_SIRKET_ADRESI = "AAAAAAAAAA";
    private static final String UPDATED_SIRKET_ADRESI = "BBBBBBBBBB";

    @Autowired
    private SirketBilgileriRepository sirketBilgileriRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSirketBilgileriMockMvc;

    private SirketBilgileri sirketBilgileri;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SirketBilgileri createEntity(EntityManager em) {
        SirketBilgileri sirketBilgileri = new SirketBilgileri()
            .sirketAdi(DEFAULT_SIRKET_ADI)
            .sirketAdresi(DEFAULT_SIRKET_ADRESI);
        // Add required entity
        Musteri musteri;
        if (TestUtil.findAll(em, Musteri.class).isEmpty()) {
            musteri = MusteriResourceIT.createEntity(em);
            em.persist(musteri);
            em.flush();
        } else {
            musteri = TestUtil.findAll(em, Musteri.class).get(0);
        }
        sirketBilgileri.setMusteri(musteri);
        return sirketBilgileri;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SirketBilgileri createUpdatedEntity(EntityManager em) {
        SirketBilgileri sirketBilgileri = new SirketBilgileri()
            .sirketAdi(UPDATED_SIRKET_ADI)
            .sirketAdresi(UPDATED_SIRKET_ADRESI);
        // Add required entity
        Musteri musteri;
        if (TestUtil.findAll(em, Musteri.class).isEmpty()) {
            musteri = MusteriResourceIT.createUpdatedEntity(em);
            em.persist(musteri);
            em.flush();
        } else {
            musteri = TestUtil.findAll(em, Musteri.class).get(0);
        }
        sirketBilgileri.setMusteri(musteri);
        return sirketBilgileri;
    }

    @BeforeEach
    public void initTest() {
        sirketBilgileri = createEntity(em);
    }

    @Test
    @Transactional
    public void createSirketBilgileri() throws Exception {
        int databaseSizeBeforeCreate = sirketBilgileriRepository.findAll().size();
        // Create the SirketBilgileri
        restSirketBilgileriMockMvc.perform(post("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sirketBilgileri)))
            .andExpect(status().isCreated());

        // Validate the SirketBilgileri in the database
        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeCreate + 1);
        SirketBilgileri testSirketBilgileri = sirketBilgileriList.get(sirketBilgileriList.size() - 1);
        assertThat(testSirketBilgileri.getSirketAdi()).isEqualTo(DEFAULT_SIRKET_ADI);
        assertThat(testSirketBilgileri.getSirketAdresi()).isEqualTo(DEFAULT_SIRKET_ADRESI);
    }

    @Test
    @Transactional
    public void createSirketBilgileriWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sirketBilgileriRepository.findAll().size();

        // Create the SirketBilgileri with an existing ID
        sirketBilgileri.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSirketBilgileriMockMvc.perform(post("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sirketBilgileri)))
            .andExpect(status().isBadRequest());

        // Validate the SirketBilgileri in the database
        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSirketAdiIsRequired() throws Exception {
        int databaseSizeBeforeTest = sirketBilgileriRepository.findAll().size();
        // set the field null
        sirketBilgileri.setSirketAdi(null);

        // Create the SirketBilgileri, which fails.


        restSirketBilgileriMockMvc.perform(post("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sirketBilgileri)))
            .andExpect(status().isBadRequest());

        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSirketAdresiIsRequired() throws Exception {
        int databaseSizeBeforeTest = sirketBilgileriRepository.findAll().size();
        // set the field null
        sirketBilgileri.setSirketAdresi(null);

        // Create the SirketBilgileri, which fails.


        restSirketBilgileriMockMvc.perform(post("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sirketBilgileri)))
            .andExpect(status().isBadRequest());

        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSirketBilgileris() throws Exception {
        // Initialize the database
        sirketBilgileriRepository.saveAndFlush(sirketBilgileri);

        // Get all the sirketBilgileriList
        restSirketBilgileriMockMvc.perform(get("/api/sirket-bilgileris?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sirketBilgileri.getId().intValue())))
            .andExpect(jsonPath("$.[*].sirketAdi").value(hasItem(DEFAULT_SIRKET_ADI)))
            .andExpect(jsonPath("$.[*].sirketAdresi").value(hasItem(DEFAULT_SIRKET_ADRESI)));
    }
    
    @Test
    @Transactional
    public void getSirketBilgileri() throws Exception {
        // Initialize the database
        sirketBilgileriRepository.saveAndFlush(sirketBilgileri);

        // Get the sirketBilgileri
        restSirketBilgileriMockMvc.perform(get("/api/sirket-bilgileris/{id}", sirketBilgileri.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sirketBilgileri.getId().intValue()))
            .andExpect(jsonPath("$.sirketAdi").value(DEFAULT_SIRKET_ADI))
            .andExpect(jsonPath("$.sirketAdresi").value(DEFAULT_SIRKET_ADRESI));
    }
    @Test
    @Transactional
    public void getNonExistingSirketBilgileri() throws Exception {
        // Get the sirketBilgileri
        restSirketBilgileriMockMvc.perform(get("/api/sirket-bilgileris/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSirketBilgileri() throws Exception {
        // Initialize the database
        sirketBilgileriRepository.saveAndFlush(sirketBilgileri);

        int databaseSizeBeforeUpdate = sirketBilgileriRepository.findAll().size();

        // Update the sirketBilgileri
        SirketBilgileri updatedSirketBilgileri = sirketBilgileriRepository.findById(sirketBilgileri.getId()).get();
        // Disconnect from session so that the updates on updatedSirketBilgileri are not directly saved in db
        em.detach(updatedSirketBilgileri);
        updatedSirketBilgileri
            .sirketAdi(UPDATED_SIRKET_ADI)
            .sirketAdresi(UPDATED_SIRKET_ADRESI);

        restSirketBilgileriMockMvc.perform(put("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSirketBilgileri)))
            .andExpect(status().isOk());

        // Validate the SirketBilgileri in the database
        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeUpdate);
        SirketBilgileri testSirketBilgileri = sirketBilgileriList.get(sirketBilgileriList.size() - 1);
        assertThat(testSirketBilgileri.getSirketAdi()).isEqualTo(UPDATED_SIRKET_ADI);
        assertThat(testSirketBilgileri.getSirketAdresi()).isEqualTo(UPDATED_SIRKET_ADRESI);
    }

    @Test
    @Transactional
    public void updateNonExistingSirketBilgileri() throws Exception {
        int databaseSizeBeforeUpdate = sirketBilgileriRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSirketBilgileriMockMvc.perform(put("/api/sirket-bilgileris")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sirketBilgileri)))
            .andExpect(status().isBadRequest());

        // Validate the SirketBilgileri in the database
        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSirketBilgileri() throws Exception {
        // Initialize the database
        sirketBilgileriRepository.saveAndFlush(sirketBilgileri);

        int databaseSizeBeforeDelete = sirketBilgileriRepository.findAll().size();

        // Delete the sirketBilgileri
        restSirketBilgileriMockMvc.perform(delete("/api/sirket-bilgileris/{id}", sirketBilgileri.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SirketBilgileri> sirketBilgileriList = sirketBilgileriRepository.findAll();
        assertThat(sirketBilgileriList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
