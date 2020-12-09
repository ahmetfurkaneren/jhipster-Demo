package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SirketBilgileri;
import com.mycompany.myapp.repository.SirketBilgileriRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SirketBilgileri}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SirketBilgileriResource {

    private final Logger log = LoggerFactory.getLogger(SirketBilgileriResource.class);

    private static final String ENTITY_NAME = "sirketBilgileri";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SirketBilgileriRepository sirketBilgileriRepository;

    public SirketBilgileriResource(SirketBilgileriRepository sirketBilgileriRepository) {
        this.sirketBilgileriRepository = sirketBilgileriRepository;
    }

    /**
     * {@code POST  /sirket-bilgileris} : Create a new sirketBilgileri.
     *
     * @param sirketBilgileri the sirketBilgileri to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sirketBilgileri, or with status {@code 400 (Bad Request)} if the sirketBilgileri has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sirket-bilgileris")
    public ResponseEntity<SirketBilgileri> createSirketBilgileri(@Valid @RequestBody SirketBilgileri sirketBilgileri) throws URISyntaxException {
        log.debug("REST request to save SirketBilgileri : {}", sirketBilgileri);
        if (sirketBilgileri.getId() != null) {
            throw new BadRequestAlertException("A new sirketBilgileri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SirketBilgileri result = sirketBilgileriRepository.save(sirketBilgileri);
        return ResponseEntity.created(new URI("/api/sirket-bilgileris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sirket-bilgileris} : Updates an existing sirketBilgileri.
     *
     * @param sirketBilgileri the sirketBilgileri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sirketBilgileri,
     * or with status {@code 400 (Bad Request)} if the sirketBilgileri is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sirketBilgileri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sirket-bilgileris")
    public ResponseEntity<SirketBilgileri> updateSirketBilgileri(@Valid @RequestBody SirketBilgileri sirketBilgileri) throws URISyntaxException {
        log.debug("REST request to update SirketBilgileri : {}", sirketBilgileri);
        if (sirketBilgileri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SirketBilgileri result = sirketBilgileriRepository.save(sirketBilgileri);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sirketBilgileri.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sirket-bilgileris} : get all the sirketBilgileris.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sirketBilgileris in body.
     */
    @GetMapping("/sirket-bilgileris")
    public List<SirketBilgileri> getAllSirketBilgileris() {
        log.debug("REST request to get all SirketBilgileris");
        return sirketBilgileriRepository.findAll();
    }

    /**
     * {@code GET  /sirket-bilgileris/:id} : get the "id" sirketBilgileri.
     *
     * @param id the id of the sirketBilgileri to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sirketBilgileri, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sirket-bilgileris/{id}")
    public ResponseEntity<SirketBilgileri> getSirketBilgileri(@PathVariable Long id) {
        log.debug("REST request to get SirketBilgileri : {}", id);
        Optional<SirketBilgileri> sirketBilgileri = sirketBilgileriRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sirketBilgileri);
    }

    /**
     * {@code DELETE  /sirket-bilgileris/:id} : delete the "id" sirketBilgileri.
     *
     * @param id the id of the sirketBilgileri to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sirket-bilgileris/{id}")
    public ResponseEntity<Void> deleteSirketBilgileri(@PathVariable Long id) {
        log.debug("REST request to delete SirketBilgileri : {}", id);
        sirketBilgileriRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
