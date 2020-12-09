package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.SmsKullanim;
import com.mycompany.myapp.domain.SozlesmeninPaketleri;
import com.mycompany.myapp.domain.TelNo;
import com.mycompany.myapp.repository.SmsKullanimRepository;

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
 * Integration tests for the {@link SmsKullanimResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SmsKullanimResourceIT {

    private static final Instant DEFAULT_TARIH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TARIH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ICERIK = "AAAAAAAAAA";
    private static final String UPDATED_ICERIK = "BBBBBBBBBB";

    @Autowired
    private SmsKullanimRepository smsKullanimRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSmsKullanimMockMvc;

    private SmsKullanim smsKullanim;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SmsKullanim createEntity(EntityManager em) {
        SmsKullanim smsKullanim = new SmsKullanim()
            .tarih(DEFAULT_TARIH)
            .icerik(DEFAULT_ICERIK);
        // Add required entity
        SozlesmeninPaketleri sozlesmeninPaketleri;
        if (TestUtil.findAll(em, SozlesmeninPaketleri.class).isEmpty()) {
            sozlesmeninPaketleri = SozlesmeninPaketleriResourceIT.createEntity(em);
            em.persist(sozlesmeninPaketleri);
            em.flush();
        } else {
            sozlesmeninPaketleri = TestUtil.findAll(em, SozlesmeninPaketleri.class).get(0);
        }
        smsKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        smsKullanim.setTelNo(telNo);
        return smsKullanim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SmsKullanim createUpdatedEntity(EntityManager em) {
        SmsKullanim smsKullanim = new SmsKullanim()
            .tarih(UPDATED_TARIH)
            .icerik(UPDATED_ICERIK);
        // Add required entity
        SozlesmeninPaketleri sozlesmeninPaketleri;
        if (TestUtil.findAll(em, SozlesmeninPaketleri.class).isEmpty()) {
            sozlesmeninPaketleri = SozlesmeninPaketleriResourceIT.createUpdatedEntity(em);
            em.persist(sozlesmeninPaketleri);
            em.flush();
        } else {
            sozlesmeninPaketleri = TestUtil.findAll(em, SozlesmeninPaketleri.class).get(0);
        }
        smsKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        // Add required entity
        TelNo telNo;
        if (TestUtil.findAll(em, TelNo.class).isEmpty()) {
            telNo = TelNoResourceIT.createUpdatedEntity(em);
            em.persist(telNo);
            em.flush();
        } else {
            telNo = TestUtil.findAll(em, TelNo.class).get(0);
        }
        smsKullanim.setTelNo(telNo);
        return smsKullanim;
    }

    @BeforeEach
    public void initTest() {
        smsKullanim = createEntity(em);
    }

    @Test
    @Transactional
    public void createSmsKullanim() throws Exception {
        int databaseSizeBeforeCreate = smsKullanimRepository.findAll().size();
        // Create the SmsKullanim
        restSmsKullanimMockMvc.perform(post("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsKullanim)))
            .andExpect(status().isCreated());

        // Validate the SmsKullanim in the database
        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeCreate + 1);
        SmsKullanim testSmsKullanim = smsKullanimList.get(smsKullanimList.size() - 1);
        assertThat(testSmsKullanim.getTarih()).isEqualTo(DEFAULT_TARIH);
        assertThat(testSmsKullanim.getIcerik()).isEqualTo(DEFAULT_ICERIK);
    }

    @Test
    @Transactional
    public void createSmsKullanimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = smsKullanimRepository.findAll().size();

        // Create the SmsKullanim with an existing ID
        smsKullanim.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSmsKullanimMockMvc.perform(post("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the SmsKullanim in the database
        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTarihIsRequired() throws Exception {
        int databaseSizeBeforeTest = smsKullanimRepository.findAll().size();
        // set the field null
        smsKullanim.setTarih(null);

        // Create the SmsKullanim, which fails.


        restSmsKullanimMockMvc.perform(post("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsKullanim)))
            .andExpect(status().isBadRequest());

        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIcerikIsRequired() throws Exception {
        int databaseSizeBeforeTest = smsKullanimRepository.findAll().size();
        // set the field null
        smsKullanim.setIcerik(null);

        // Create the SmsKullanim, which fails.


        restSmsKullanimMockMvc.perform(post("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsKullanim)))
            .andExpect(status().isBadRequest());

        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSmsKullanims() throws Exception {
        // Initialize the database
        smsKullanimRepository.saveAndFlush(smsKullanim);

        // Get all the smsKullanimList
        restSmsKullanimMockMvc.perform(get("/api/sms-kullanims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(smsKullanim.getId().intValue())))
            .andExpect(jsonPath("$.[*].tarih").value(hasItem(DEFAULT_TARIH.toString())))
            .andExpect(jsonPath("$.[*].icerik").value(hasItem(DEFAULT_ICERIK)));
    }
    
    @Test
    @Transactional
    public void getSmsKullanim() throws Exception {
        // Initialize the database
        smsKullanimRepository.saveAndFlush(smsKullanim);

        // Get the smsKullanim
        restSmsKullanimMockMvc.perform(get("/api/sms-kullanims/{id}", smsKullanim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(smsKullanim.getId().intValue()))
            .andExpect(jsonPath("$.tarih").value(DEFAULT_TARIH.toString()))
            .andExpect(jsonPath("$.icerik").value(DEFAULT_ICERIK));
    }
    @Test
    @Transactional
    public void getNonExistingSmsKullanim() throws Exception {
        // Get the smsKullanim
        restSmsKullanimMockMvc.perform(get("/api/sms-kullanims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSmsKullanim() throws Exception {
        // Initialize the database
        smsKullanimRepository.saveAndFlush(smsKullanim);

        int databaseSizeBeforeUpdate = smsKullanimRepository.findAll().size();

        // Update the smsKullanim
        SmsKullanim updatedSmsKullanim = smsKullanimRepository.findById(smsKullanim.getId()).get();
        // Disconnect from session so that the updates on updatedSmsKullanim are not directly saved in db
        em.detach(updatedSmsKullanim);
        updatedSmsKullanim
            .tarih(UPDATED_TARIH)
            .icerik(UPDATED_ICERIK);

        restSmsKullanimMockMvc.perform(put("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSmsKullanim)))
            .andExpect(status().isOk());

        // Validate the SmsKullanim in the database
        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeUpdate);
        SmsKullanim testSmsKullanim = smsKullanimList.get(smsKullanimList.size() - 1);
        assertThat(testSmsKullanim.getTarih()).isEqualTo(UPDATED_TARIH);
        assertThat(testSmsKullanim.getIcerik()).isEqualTo(UPDATED_ICERIK);
    }

    @Test
    @Transactional
    public void updateNonExistingSmsKullanim() throws Exception {
        int databaseSizeBeforeUpdate = smsKullanimRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSmsKullanimMockMvc.perform(put("/api/sms-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(smsKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the SmsKullanim in the database
        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSmsKullanim() throws Exception {
        // Initialize the database
        smsKullanimRepository.saveAndFlush(smsKullanim);

        int databaseSizeBeforeDelete = smsKullanimRepository.findAll().size();

        // Delete the smsKullanim
        restSmsKullanimMockMvc.perform(delete("/api/sms-kullanims/{id}", smsKullanim.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SmsKullanim> smsKullanimList = smsKullanimRepository.findAll();
        assertThat(smsKullanimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
