package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.DakikaKullanim;
import com.mycompany.myapp.domain.SozlesmeninPaketleri;
import com.mycompany.myapp.domain.TelNo;
import com.mycompany.myapp.repository.DakikaKullanimRepository;

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
 * Integration tests for the {@link DakikaKullanimResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DakikaKullanimResourceIT {

    private static final Instant DEFAULT_TARIH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TARIH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_MIKTAR = 1;
    private static final Integer UPDATED_MIKTAR = 2;

    @Autowired
    private DakikaKullanimRepository dakikaKullanimRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDakikaKullanimMockMvc;

    private DakikaKullanim dakikaKullanim;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DakikaKullanim createEntity(EntityManager em) {
        DakikaKullanim dakikaKullanim = new DakikaKullanim()
            .tarih(DEFAULT_TARIH)
            .miktar(DEFAULT_MIKTAR);
        // Add required entity
        SozlesmeninPaketleri sozlesmeninPaketleri;
        if (TestUtil.findAll(em, SozlesmeninPaketleri.class).isEmpty()) {
            sozlesmeninPaketleri = SozlesmeninPaketleriResourceIT.createEntity(em);
            em.persist(sozlesmeninPaketleri);
            em.flush();
        } else {
            sozlesmeninPaketleri = TestUtil.findAll(em, SozlesmeninPaketleri.class).get(0);
        }
        dakikaKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        dakikaKullanim.setTelNo(telNo);
        return dakikaKullanim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DakikaKullanim createUpdatedEntity(EntityManager em) {
        DakikaKullanim dakikaKullanim = new DakikaKullanim()
            .tarih(UPDATED_TARIH)
            .miktar(UPDATED_MIKTAR);
        // Add required entity
        SozlesmeninPaketleri sozlesmeninPaketleri;
        if (TestUtil.findAll(em, SozlesmeninPaketleri.class).isEmpty()) {
            sozlesmeninPaketleri = SozlesmeninPaketleriResourceIT.createUpdatedEntity(em);
            em.persist(sozlesmeninPaketleri);
            em.flush();
        } else {
            sozlesmeninPaketleri = TestUtil.findAll(em, SozlesmeninPaketleri.class).get(0);
        }
        dakikaKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createUpdatedEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        dakikaKullanim.setTelNo(telNo);
        return dakikaKullanim;
    }

    @BeforeEach
    public void initTest() {
        dakikaKullanim = createEntity(em);
    }

    @Test
    @Transactional
    public void createDakikaKullanim() throws Exception {
        int databaseSizeBeforeCreate = dakikaKullanimRepository.findAll().size();
        // Create the DakikaKullanim
        restDakikaKullanimMockMvc.perform(post("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dakikaKullanim)))
            .andExpect(status().isCreated());

        // Validate the DakikaKullanim in the database
        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeCreate + 1);
        DakikaKullanim testDakikaKullanim = dakikaKullanimList.get(dakikaKullanimList.size() - 1);
        assertThat(testDakikaKullanim.getTarih()).isEqualTo(DEFAULT_TARIH);
        assertThat(testDakikaKullanim.getMiktar()).isEqualTo(DEFAULT_MIKTAR);
    }

    @Test
    @Transactional
    public void createDakikaKullanimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dakikaKullanimRepository.findAll().size();

        // Create the DakikaKullanim with an existing ID
        dakikaKullanim.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDakikaKullanimMockMvc.perform(post("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dakikaKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the DakikaKullanim in the database
        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTarihIsRequired() throws Exception {
        int databaseSizeBeforeTest = dakikaKullanimRepository.findAll().size();
        // set the field null
        dakikaKullanim.setTarih(null);

        // Create the DakikaKullanim, which fails.


        restDakikaKullanimMockMvc.perform(post("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dakikaKullanim)))
            .andExpect(status().isBadRequest());

        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiktarIsRequired() throws Exception {
        int databaseSizeBeforeTest = dakikaKullanimRepository.findAll().size();
        // set the field null
        dakikaKullanim.setMiktar(null);

        // Create the DakikaKullanim, which fails.


        restDakikaKullanimMockMvc.perform(post("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dakikaKullanim)))
            .andExpect(status().isBadRequest());

        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDakikaKullanims() throws Exception {
        // Initialize the database
        dakikaKullanimRepository.saveAndFlush(dakikaKullanim);

        // Get all the dakikaKullanimList
        restDakikaKullanimMockMvc.perform(get("/api/dakika-kullanims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dakikaKullanim.getId().intValue())))
            .andExpect(jsonPath("$.[*].tarih").value(hasItem(DEFAULT_TARIH.toString())))
            .andExpect(jsonPath("$.[*].miktar").value(hasItem(DEFAULT_MIKTAR)));
    }
    
    @Test
    @Transactional
    public void getDakikaKullanim() throws Exception {
        // Initialize the database
        dakikaKullanimRepository.saveAndFlush(dakikaKullanim);

        // Get the dakikaKullanim
        restDakikaKullanimMockMvc.perform(get("/api/dakika-kullanims/{id}", dakikaKullanim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dakikaKullanim.getId().intValue()))
            .andExpect(jsonPath("$.tarih").value(DEFAULT_TARIH.toString()))
            .andExpect(jsonPath("$.miktar").value(DEFAULT_MIKTAR));
    }
    @Test
    @Transactional
    public void getNonExistingDakikaKullanim() throws Exception {
        // Get the dakikaKullanim
        restDakikaKullanimMockMvc.perform(get("/api/dakika-kullanims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDakikaKullanim() throws Exception {
        // Initialize the database
        dakikaKullanimRepository.saveAndFlush(dakikaKullanim);

        int databaseSizeBeforeUpdate = dakikaKullanimRepository.findAll().size();

        // Update the dakikaKullanim
        DakikaKullanim updatedDakikaKullanim = dakikaKullanimRepository.findById(dakikaKullanim.getId()).get();
        // Disconnect from session so that the updates on updatedDakikaKullanim are not directly saved in db
        em.detach(updatedDakikaKullanim);
        updatedDakikaKullanim
            .tarih(UPDATED_TARIH)
            .miktar(UPDATED_MIKTAR);

        restDakikaKullanimMockMvc.perform(put("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDakikaKullanim)))
            .andExpect(status().isOk());

        // Validate the DakikaKullanim in the database
        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeUpdate);
        DakikaKullanim testDakikaKullanim = dakikaKullanimList.get(dakikaKullanimList.size() - 1);
        assertThat(testDakikaKullanim.getTarih()).isEqualTo(UPDATED_TARIH);
        assertThat(testDakikaKullanim.getMiktar()).isEqualTo(UPDATED_MIKTAR);
    }

    @Test
    @Transactional
    public void updateNonExistingDakikaKullanim() throws Exception {
        int databaseSizeBeforeUpdate = dakikaKullanimRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDakikaKullanimMockMvc.perform(put("/api/dakika-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dakikaKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the DakikaKullanim in the database
        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDakikaKullanim() throws Exception {
        // Initialize the database
        dakikaKullanimRepository.saveAndFlush(dakikaKullanim);

        int databaseSizeBeforeDelete = dakikaKullanimRepository.findAll().size();

        // Delete the dakikaKullanim
        restDakikaKullanimMockMvc.perform(delete("/api/dakika-kullanims/{id}", dakikaKullanim.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DakikaKullanim> dakikaKullanimList = dakikaKullanimRepository.findAll();
        assertThat(dakikaKullanimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
