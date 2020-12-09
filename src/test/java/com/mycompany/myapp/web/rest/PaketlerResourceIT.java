package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Paketler;
import com.mycompany.myapp.repository.PaketlerRepository;

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

import com.mycompany.myapp.domain.enumeration.Aktif;
import com.mycompany.myapp.domain.enumeration.Tip;
import com.mycompany.myapp.domain.enumeration.PaketTipi;
import com.mycompany.myapp.domain.enumeration.Donem;
/**
 * Integration tests for the {@link PaketlerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaketlerResourceIT {

    private static final String DEFAULT_PAKET_ADI = "AAAAAAAAAA";
    private static final String UPDATED_PAKET_ADI = "BBBBBBBBBB";

    private static final String DEFAULT_ACIKLAMA = "AAAAAAAAAA";
    private static final String UPDATED_ACIKLAMA = "BBBBBBBBBB";

    private static final Instant DEFAULT_BASLANGIC_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BASLANGIC_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_BITIS_TARIHI = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BITIS_TARIHI = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_FIYAT = 1L;
    private static final Long UPDATED_FIYAT = 2L;

    private static final Long DEFAULT_YENI_MUSTERI_FIYAT = 1L;
    private static final Long UPDATED_YENI_MUSTERI_FIYAT = 2L;

    private static final Integer DEFAULT_TAHAHUT_SURE = 1;
    private static final Integer UPDATED_TAHAHUT_SURE = 2;

    private static final Integer DEFAULT_DAKIKA = 1;
    private static final Integer UPDATED_DAKIKA = 2;

    private static final Integer DEFAULT_SMS = 1;
    private static final Integer UPDATED_SMS = 2;

    private static final Integer DEFAULT_INTERNET = 1;
    private static final Integer UPDATED_INTERNET = 2;

    private static final Aktif DEFAULT_AKTIF = Aktif.Akrif;
    private static final Aktif UPDATED_AKTIF = Aktif.Pasif;

    private static final Tip DEFAULT_TIP = Tip.AnaPaket;
    private static final Tip UPDATED_TIP = Tip.EkPaket;

    private static final PaketTipi DEFAULT_PAKET_TIPI = PaketTipi.Faturali;
    private static final PaketTipi UPDATED_PAKET_TIPI = PaketTipi.Faturasiz;

    private static final Donem DEFAULT_DONEM = Donem.Aylik;
    private static final Donem UPDATED_DONEM = Donem.Haftalik;

    private static final Long DEFAULT_DAKIKA_UCRET = 1L;
    private static final Long UPDATED_DAKIKA_UCRET = 2L;

    private static final Long DEFAULT_SMS_UCRET = 1L;
    private static final Long UPDATED_SMS_UCRET = 2L;

    private static final Long DEFAULT_INTERNET_UCRET = 1L;
    private static final Long UPDATED_INTERNET_UCRET = 2L;

    @Autowired
    private PaketlerRepository paketlerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaketlerMockMvc;

    private Paketler paketler;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paketler createEntity(EntityManager em) {
        Paketler paketler = new Paketler()
            .paketAdi(DEFAULT_PAKET_ADI)
            .aciklama(DEFAULT_ACIKLAMA)
            .baslangicTarihi(DEFAULT_BASLANGIC_TARIHI)
            .bitisTarihi(DEFAULT_BITIS_TARIHI)
            .fiyat(DEFAULT_FIYAT)
            .yeniMusteriFiyat(DEFAULT_YENI_MUSTERI_FIYAT)
            .tahahutSure(DEFAULT_TAHAHUT_SURE)
            .dakika(DEFAULT_DAKIKA)
            .sms(DEFAULT_SMS)
            .internet(DEFAULT_INTERNET)
            .aktif(DEFAULT_AKTIF)
            .tip(DEFAULT_TIP)
            .paketTipi(DEFAULT_PAKET_TIPI)
            .donem(DEFAULT_DONEM)
            .dakikaUcret(DEFAULT_DAKIKA_UCRET)
            .smsUcret(DEFAULT_SMS_UCRET)
            .internetUcret(DEFAULT_INTERNET_UCRET);
        return paketler;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paketler createUpdatedEntity(EntityManager em) {
        Paketler paketler = new Paketler()
            .paketAdi(UPDATED_PAKET_ADI)
            .aciklama(UPDATED_ACIKLAMA)
            .baslangicTarihi(UPDATED_BASLANGIC_TARIHI)
            .bitisTarihi(UPDATED_BITIS_TARIHI)
            .fiyat(UPDATED_FIYAT)
            .yeniMusteriFiyat(UPDATED_YENI_MUSTERI_FIYAT)
            .tahahutSure(UPDATED_TAHAHUT_SURE)
            .dakika(UPDATED_DAKIKA)
            .sms(UPDATED_SMS)
            .internet(UPDATED_INTERNET)
            .aktif(UPDATED_AKTIF)
            .tip(UPDATED_TIP)
            .paketTipi(UPDATED_PAKET_TIPI)
            .donem(UPDATED_DONEM)
            .dakikaUcret(UPDATED_DAKIKA_UCRET)
            .smsUcret(UPDATED_SMS_UCRET)
            .internetUcret(UPDATED_INTERNET_UCRET);
        return paketler;
    }

    @BeforeEach
    public void initTest() {
        paketler = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaketler() throws Exception {
        int databaseSizeBeforeCreate = paketlerRepository.findAll().size();
        // Create the Paketler
        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isCreated());

        // Validate the Paketler in the database
        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeCreate + 1);
        Paketler testPaketler = paketlerList.get(paketlerList.size() - 1);
        assertThat(testPaketler.getPaketAdi()).isEqualTo(DEFAULT_PAKET_ADI);
        assertThat(testPaketler.getAciklama()).isEqualTo(DEFAULT_ACIKLAMA);
        assertThat(testPaketler.getBaslangicTarihi()).isEqualTo(DEFAULT_BASLANGIC_TARIHI);
        assertThat(testPaketler.getBitisTarihi()).isEqualTo(DEFAULT_BITIS_TARIHI);
        assertThat(testPaketler.getFiyat()).isEqualTo(DEFAULT_FIYAT);
        assertThat(testPaketler.getYeniMusteriFiyat()).isEqualTo(DEFAULT_YENI_MUSTERI_FIYAT);
        assertThat(testPaketler.getTahahutSure()).isEqualTo(DEFAULT_TAHAHUT_SURE);
        assertThat(testPaketler.getDakika()).isEqualTo(DEFAULT_DAKIKA);
        assertThat(testPaketler.getSms()).isEqualTo(DEFAULT_SMS);
        assertThat(testPaketler.getInternet()).isEqualTo(DEFAULT_INTERNET);
        assertThat(testPaketler.getAktif()).isEqualTo(DEFAULT_AKTIF);
        assertThat(testPaketler.getTip()).isEqualTo(DEFAULT_TIP);
        assertThat(testPaketler.getPaketTipi()).isEqualTo(DEFAULT_PAKET_TIPI);
        assertThat(testPaketler.getDonem()).isEqualTo(DEFAULT_DONEM);
        assertThat(testPaketler.getDakikaUcret()).isEqualTo(DEFAULT_DAKIKA_UCRET);
        assertThat(testPaketler.getSmsUcret()).isEqualTo(DEFAULT_SMS_UCRET);
        assertThat(testPaketler.getInternetUcret()).isEqualTo(DEFAULT_INTERNET_UCRET);
    }

    @Test
    @Transactional
    public void createPaketlerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paketlerRepository.findAll().size();

        // Create the Paketler with an existing ID
        paketler.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        // Validate the Paketler in the database
        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPaketAdiIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setPaketAdi(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaslangicTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setBaslangicTarihi(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBitisTarihiIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setBitisTarihi(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFiyatIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setFiyat(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkYeniMusteriFiyatIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setYeniMusteriFiyat(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDonemIsRequired() throws Exception {
        int databaseSizeBeforeTest = paketlerRepository.findAll().size();
        // set the field null
        paketler.setDonem(null);

        // Create the Paketler, which fails.


        restPaketlerMockMvc.perform(post("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaketlers() throws Exception {
        // Initialize the database
        paketlerRepository.saveAndFlush(paketler);

        // Get all the paketlerList
        restPaketlerMockMvc.perform(get("/api/paketlers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paketler.getId().intValue())))
            .andExpect(jsonPath("$.[*].paketAdi").value(hasItem(DEFAULT_PAKET_ADI)))
            .andExpect(jsonPath("$.[*].aciklama").value(hasItem(DEFAULT_ACIKLAMA)))
            .andExpect(jsonPath("$.[*].baslangicTarihi").value(hasItem(DEFAULT_BASLANGIC_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].bitisTarihi").value(hasItem(DEFAULT_BITIS_TARIHI.toString())))
            .andExpect(jsonPath("$.[*].fiyat").value(hasItem(DEFAULT_FIYAT.intValue())))
            .andExpect(jsonPath("$.[*].yeniMusteriFiyat").value(hasItem(DEFAULT_YENI_MUSTERI_FIYAT.intValue())))
            .andExpect(jsonPath("$.[*].tahahutSure").value(hasItem(DEFAULT_TAHAHUT_SURE)))
            .andExpect(jsonPath("$.[*].dakika").value(hasItem(DEFAULT_DAKIKA)))
            .andExpect(jsonPath("$.[*].sms").value(hasItem(DEFAULT_SMS)))
            .andExpect(jsonPath("$.[*].internet").value(hasItem(DEFAULT_INTERNET)))
            .andExpect(jsonPath("$.[*].aktif").value(hasItem(DEFAULT_AKTIF.toString())))
            .andExpect(jsonPath("$.[*].tip").value(hasItem(DEFAULT_TIP.toString())))
            .andExpect(jsonPath("$.[*].paketTipi").value(hasItem(DEFAULT_PAKET_TIPI.toString())))
            .andExpect(jsonPath("$.[*].donem").value(hasItem(DEFAULT_DONEM.toString())))
            .andExpect(jsonPath("$.[*].dakikaUcret").value(hasItem(DEFAULT_DAKIKA_UCRET.intValue())))
            .andExpect(jsonPath("$.[*].smsUcret").value(hasItem(DEFAULT_SMS_UCRET.intValue())))
            .andExpect(jsonPath("$.[*].internetUcret").value(hasItem(DEFAULT_INTERNET_UCRET.intValue())));
    }
    
    @Test
    @Transactional
    public void getPaketler() throws Exception {
        // Initialize the database
        paketlerRepository.saveAndFlush(paketler);

        // Get the paketler
        restPaketlerMockMvc.perform(get("/api/paketlers/{id}", paketler.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paketler.getId().intValue()))
            .andExpect(jsonPath("$.paketAdi").value(DEFAULT_PAKET_ADI))
            .andExpect(jsonPath("$.aciklama").value(DEFAULT_ACIKLAMA))
            .andExpect(jsonPath("$.baslangicTarihi").value(DEFAULT_BASLANGIC_TARIHI.toString()))
            .andExpect(jsonPath("$.bitisTarihi").value(DEFAULT_BITIS_TARIHI.toString()))
            .andExpect(jsonPath("$.fiyat").value(DEFAULT_FIYAT.intValue()))
            .andExpect(jsonPath("$.yeniMusteriFiyat").value(DEFAULT_YENI_MUSTERI_FIYAT.intValue()))
            .andExpect(jsonPath("$.tahahutSure").value(DEFAULT_TAHAHUT_SURE))
            .andExpect(jsonPath("$.dakika").value(DEFAULT_DAKIKA))
            .andExpect(jsonPath("$.sms").value(DEFAULT_SMS))
            .andExpect(jsonPath("$.internet").value(DEFAULT_INTERNET))
            .andExpect(jsonPath("$.aktif").value(DEFAULT_AKTIF.toString()))
            .andExpect(jsonPath("$.tip").value(DEFAULT_TIP.toString()))
            .andExpect(jsonPath("$.paketTipi").value(DEFAULT_PAKET_TIPI.toString()))
            .andExpect(jsonPath("$.donem").value(DEFAULT_DONEM.toString()))
            .andExpect(jsonPath("$.dakikaUcret").value(DEFAULT_DAKIKA_UCRET.intValue()))
            .andExpect(jsonPath("$.smsUcret").value(DEFAULT_SMS_UCRET.intValue()))
            .andExpect(jsonPath("$.internetUcret").value(DEFAULT_INTERNET_UCRET.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPaketler() throws Exception {
        // Get the paketler
        restPaketlerMockMvc.perform(get("/api/paketlers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaketler() throws Exception {
        // Initialize the database
        paketlerRepository.saveAndFlush(paketler);

        int databaseSizeBeforeUpdate = paketlerRepository.findAll().size();

        // Update the paketler
        Paketler updatedPaketler = paketlerRepository.findById(paketler.getId()).get();
        // Disconnect from session so that the updates on updatedPaketler are not directly saved in db
        em.detach(updatedPaketler);
        updatedPaketler
            .paketAdi(UPDATED_PAKET_ADI)
            .aciklama(UPDATED_ACIKLAMA)
            .baslangicTarihi(UPDATED_BASLANGIC_TARIHI)
            .bitisTarihi(UPDATED_BITIS_TARIHI)
            .fiyat(UPDATED_FIYAT)
            .yeniMusteriFiyat(UPDATED_YENI_MUSTERI_FIYAT)
            .tahahutSure(UPDATED_TAHAHUT_SURE)
            .dakika(UPDATED_DAKIKA)
            .sms(UPDATED_SMS)
            .internet(UPDATED_INTERNET)
            .aktif(UPDATED_AKTIF)
            .tip(UPDATED_TIP)
            .paketTipi(UPDATED_PAKET_TIPI)
            .donem(UPDATED_DONEM)
            .dakikaUcret(UPDATED_DAKIKA_UCRET)
            .smsUcret(UPDATED_SMS_UCRET)
            .internetUcret(UPDATED_INTERNET_UCRET);

        restPaketlerMockMvc.perform(put("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPaketler)))
            .andExpect(status().isOk());

        // Validate the Paketler in the database
        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeUpdate);
        Paketler testPaketler = paketlerList.get(paketlerList.size() - 1);
        assertThat(testPaketler.getPaketAdi()).isEqualTo(UPDATED_PAKET_ADI);
        assertThat(testPaketler.getAciklama()).isEqualTo(UPDATED_ACIKLAMA);
        assertThat(testPaketler.getBaslangicTarihi()).isEqualTo(UPDATED_BASLANGIC_TARIHI);
        assertThat(testPaketler.getBitisTarihi()).isEqualTo(UPDATED_BITIS_TARIHI);
        assertThat(testPaketler.getFiyat()).isEqualTo(UPDATED_FIYAT);
        assertThat(testPaketler.getYeniMusteriFiyat()).isEqualTo(UPDATED_YENI_MUSTERI_FIYAT);
        assertThat(testPaketler.getTahahutSure()).isEqualTo(UPDATED_TAHAHUT_SURE);
        assertThat(testPaketler.getDakika()).isEqualTo(UPDATED_DAKIKA);
        assertThat(testPaketler.getSms()).isEqualTo(UPDATED_SMS);
        assertThat(testPaketler.getInternet()).isEqualTo(UPDATED_INTERNET);
        assertThat(testPaketler.getAktif()).isEqualTo(UPDATED_AKTIF);
        assertThat(testPaketler.getTip()).isEqualTo(UPDATED_TIP);
        assertThat(testPaketler.getPaketTipi()).isEqualTo(UPDATED_PAKET_TIPI);
        assertThat(testPaketler.getDonem()).isEqualTo(UPDATED_DONEM);
        assertThat(testPaketler.getDakikaUcret()).isEqualTo(UPDATED_DAKIKA_UCRET);
        assertThat(testPaketler.getSmsUcret()).isEqualTo(UPDATED_SMS_UCRET);
        assertThat(testPaketler.getInternetUcret()).isEqualTo(UPDATED_INTERNET_UCRET);
    }

    @Test
    @Transactional
    public void updateNonExistingPaketler() throws Exception {
        int databaseSizeBeforeUpdate = paketlerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaketlerMockMvc.perform(put("/api/paketlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paketler)))
            .andExpect(status().isBadRequest());

        // Validate the Paketler in the database
        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaketler() throws Exception {
        // Initialize the database
        paketlerRepository.saveAndFlush(paketler);

        int databaseSizeBeforeDelete = paketlerRepository.findAll().size();

        // Delete the paketler
        restPaketlerMockMvc.perform(delete("/api/paketlers/{id}", paketler.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Paketler> paketlerList = paketlerRepository.findAll();
        assertThat(paketlerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
