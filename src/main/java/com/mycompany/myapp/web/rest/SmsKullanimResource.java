package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SmsKullanim;
import com.mycompany.myapp.repository.SmsKullanimRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.SmsKullanim}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SmsKullanimResource {

    private final Logger log = LoggerFactory.getLogger(SmsKullanimResource.class);

    private static final String ENTITY_NAME = "smsKullanim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SmsKullanimRepository smsKullanimRepository;

    public SmsKullanimResource(SmsKullanimRepository smsKullanimRepository) {
        this.smsKullanimRepository = smsKullanimRepository;
    }

    /**
     * {@code POST  /sms-kullanims} : Create a new smsKullanim.
     *
     * @param smsKullanim the smsKullanim to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new smsKullanim, or with status {@code 400 (Bad Request)} if the smsKullanim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sms-kullanims")
    public ResponseEntity<SmsKullanim> createSmsKullanim(@Valid @RequestBody SmsKullanim smsKullanim) throws URISyntaxException {
        log.debug("REST request to save SmsKullanim : {}", smsKullanim);
        if (smsKullanim.getId() != null) {
            throw new BadRequestAlertException("A new smsKullanim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SmsKullanim result = smsKullanimRepository.save(smsKullanim);
        return ResponseEntity.created(new URI("/api/sms-kullanims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sms-kullanims} : Updates an existing smsKullanim.
     *
     * @param smsKullanim the smsKullanim to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated smsKullanim,
     * or with status {@code 400 (Bad Request)} if the smsKullanim is not valid,
     * or with status {@code 500 (Internal Server Error)} if the smsKullanim couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sms-kullanims")
    public ResponseEntity<SmsKullanim> updateSmsKullanim(@Valid @RequestBody SmsKullanim smsKullanim) throws URISyntaxException {
        log.debug("REST request to update SmsKullanim : {}", smsKullanim);
        if (smsKullanim.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SmsKullanim result = smsKullanimRepository.save(smsKullanim);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, smsKullanim.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sms-kullanims} : get all the smsKullanims.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of smsKullanims in body.
     */
    @GetMapping("/sms-kullanims")
    public List<SmsKullanim> getAllSmsKullanims() {
        log.debug("REST request to get all SmsKullanims");
        return smsKullanimRepository.findAll();
    }

    /**
     * {@code GET  /sms-kullanims/:id} : get the "id" smsKullanim.
     *
     * @param id the id of the smsKullanim to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the smsKullanim, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sms-kullanims/{id}")
    public ResponseEntity<SmsKullanim> getSmsKullanim(@PathVariable Long id) {
        log.debug("REST request to get SmsKullanim : {}", id);
        Optional<SmsKullanim> smsKullanim = smsKullanimRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(smsKullanim);
    }

    /**
     * {@code DELETE  /sms-kullanims/:id} : delete the "id" smsKullanim.
     *
     * @param id the id of the smsKullanim to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sms-kullanims/{id}")
    public ResponseEntity<Void> deleteSmsKullanim(@PathVariable Long id) {
        log.debug("REST request to delete SmsKullanim : {}", id);
        smsKullanimRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
