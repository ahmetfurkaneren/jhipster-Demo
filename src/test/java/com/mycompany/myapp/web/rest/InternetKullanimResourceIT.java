package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.InternetKullanim;
import com.mycompany.myapp.domain.SozlesmeninPaketleri;
import com.mycompany.myapp.repository.InternetKullanimRepository;

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
 * Integration tests for the {@link InternetKullanimResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InternetKullanimResourceIT {

    private static final Instant DEFAULT_TARIH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TARIH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MIKTAR = 1L;
    private static final Long UPDATED_MIKTAR = 2L;

    @Autowired
    private InternetKullanimRepository internetKullanimRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInternetKullanimMockMvc;

    private InternetKullanim internetKullanim;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InternetKullanim createEntity(EntityManager em) {
        InternetKullanim internetKullanim = new InternetKullanim()
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
        internetKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        return internetKullanim;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InternetKullanim createUpdatedEntity(EntityManager em) {
        InternetKullanim internetKullanim = new InternetKullanim()
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
        internetKullanim.setSozlesmeninPaketleri(sozlesmeninPaketleri);
        return internetKullanim;
    }

    @BeforeEach
    public void initTest() {
        internetKullanim = createEntity(em);
    }

    @Test
    @Transactional
    public void createInternetKullanim() throws Exception {
        int databaseSizeBeforeCreate = internetKullanimRepository.findAll().size();
        // Create the InternetKullanim
        restInternetKullanimMockMvc.perform(post("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internetKullanim)))
            .andExpect(status().isCreated());

        // Validate the InternetKullanim in the database
        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeCreate + 1);
        InternetKullanim testInternetKullanim = internetKullanimList.get(internetKullanimList.size() - 1);
        assertThat(testInternetKullanim.getTarih()).isEqualTo(DEFAULT_TARIH);
        assertThat(testInternetKullanim.getMiktar()).isEqualTo(DEFAULT_MIKTAR);
    }

    @Test
    @Transactional
    public void createInternetKullanimWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = internetKullanimRepository.findAll().size();

        // Create the InternetKullanim with an existing ID
        internetKullanim.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInternetKullanimMockMvc.perform(post("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internetKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the InternetKullanim in the database
        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTarihIsRequired() throws Exception {
        int databaseSizeBeforeTest = internetKullanimRepository.findAll().size();
        // set the field null
        internetKullanim.setTarih(null);

        // Create the InternetKullanim, which fails.


        restInternetKullanimMockMvc.perform(post("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internetKullanim)))
            .andExpect(status().isBadRequest());

        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiktarIsRequired() throws Exception {
        int databaseSizeBeforeTest = internetKullanimRepository.findAll().size();
        // set the field null
        internetKullanim.setMiktar(null);

        // Create the InternetKullanim, which fails.


        restInternetKullanimMockMvc.perform(post("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internetKullanim)))
            .andExpect(status().isBadRequest());

        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInternetKullanims() throws Exception {
        // Initialize the database
        internetKullanimRepository.saveAndFlush(internetKullanim);

        // Get all the internetKullanimList
        restInternetKullanimMockMvc.perform(get("/api/internet-kullanims?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(internetKullanim.getId().intValue())))
            .andExpect(jsonPath("$.[*].tarih").value(hasItem(DEFAULT_TARIH.toString())))
            .andExpect(jsonPath("$.[*].miktar").value(hasItem(DEFAULT_MIKTAR.intValue())));
    }
    
    @Test
    @Transactional
    public void getInternetKullanim() throws Exception {
        // Initialize the database
        internetKullanimRepository.saveAndFlush(internetKullanim);

        // Get the internetKullanim
        restInternetKullanimMockMvc.perform(get("/api/internet-kullanims/{id}", internetKullanim.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(internetKullanim.getId().intValue()))
            .andExpect(jsonPath("$.tarih").value(DEFAULT_TARIH.toString()))
            .andExpect(jsonPath("$.miktar").value(DEFAULT_MIKTAR.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInternetKullanim() throws Exception {
        // Get the internetKullanim
        restInternetKullanimMockMvc.perform(get("/api/internet-kullanims/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInternetKullanim() throws Exception {
        // Initialize the database
        internetKullanimRepository.saveAndFlush(internetKullanim);

        int databaseSizeBeforeUpdate = internetKullanimRepository.findAll().size();

        // Update the internetKullanim
        InternetKullanim updatedInternetKullanim = internetKullanimRepository.findById(internetKullanim.getId()).get();
        // Disconnect from session so that the updates on updatedInternetKullanim are not directly saved in db
        em.detach(updatedInternetKullanim);
        updatedInternetKullanim
            .tarih(UPDATED_TARIH)
            .miktar(UPDATED_MIKTAR);

        restInternetKullanimMockMvc.perform(put("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInternetKullanim)))
            .andExpect(status().isOk());

        // Validate the InternetKullanim in the database
        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeUpdate);
        InternetKullanim testInternetKullanim = internetKullanimList.get(internetKullanimList.size() - 1);
        assertThat(testInternetKullanim.getTarih()).isEqualTo(UPDATED_TARIH);
        assertThat(testInternetKullanim.getMiktar()).isEqualTo(UPDATED_MIKTAR);
    }

    @Test
    @Transactional
    public void updateNonExistingInternetKullanim() throws Exception {
        int databaseSizeBeforeUpdate = internetKullanimRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInternetKullanimMockMvc.perform(put("/api/internet-kullanims")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(internetKullanim)))
            .andExpect(status().isBadRequest());

        // Validate the InternetKullanim in the database
        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInternetKullanim() throws Exception {
        // Initialize the database
        internetKullanimRepository.saveAndFlush(internetKullanim);

        int databaseSizeBeforeDelete = internetKullanimRepository.findAll().size();

        // Delete the internetKullanim
        restInternetKullanimMockMvc.perform(delete("/api/internet-kullanims/{id}", internetKullanim.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InternetKullanim> internetKullanimList = internetKullanimRepository.findAll();
        assertThat(internetKullanimList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
