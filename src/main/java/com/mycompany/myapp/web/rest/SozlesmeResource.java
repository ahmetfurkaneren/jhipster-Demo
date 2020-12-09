package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Sozlesme;
import com.mycompany.myapp.repository.SozlesmeRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Sozlesme}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SozlesmeResource {

    private final Logger log = LoggerFactory.getLogger(SozlesmeResource.class);

    private static final String ENTITY_NAME = "sozlesme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SozlesmeRepository sozlesmeRepository;

    public SozlesmeResource(SozlesmeRepository sozlesmeRepository) {
        this.sozlesmeRepository = sozlesmeRepository;
    }

    /**
     * {@code POST  /sozlesmes} : Create a new sozlesme.
     *
     * @param sozlesme the sozlesme to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sozlesme, or with status {@code 400 (Bad Request)} if the sozlesme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sozlesmes")
    public ResponseEntity<Sozlesme> createSozlesme(@Valid @RequestBody Sozlesme sozlesme) throws URISyntaxException {
        log.debug("REST request to save Sozlesme : {}", sozlesme);
        if (sozlesme.getId() != null) {
            throw new BadRequestAlertException("A new sozlesme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sozlesme result = sozlesmeRepository.save(sozlesme);
        return ResponseEntity.created(new URI("/api/sozlesmes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sozlesmes} : Updates an existing sozlesme.
     *
     * @param sozlesme the sozlesme to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sozlesme,
     * or with status {@code 400 (Bad Request)} if the sozlesme is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sozlesme couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sozlesmes")
    public ResponseEntity<Sozlesme> updateSozlesme(@Valid @RequestBody Sozlesme sozlesme) throws URISyntaxException {
        log.debug("REST request to update Sozlesme : {}", sozlesme);
        if (sozlesme.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sozlesme result = sozlesmeRepository.save(sozlesme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sozlesme.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sozlesmes} : get all the sozlesmes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sozlesmes in body.
     */
    @GetMapping("/sozlesmes")
    public List<Sozlesme> getAllSozlesmes() {
        log.debug("REST request to get all Sozlesmes");
        return sozlesmeRepository.findAll();
    }

    /**
     * {@code GET  /sozlesmes/:id} : get the "id" sozlesme.
     *
     * @param id the id of the sozlesme to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sozlesme, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sozlesmes/{id}")
    public ResponseEntity<Sozlesme> getSozlesme(@PathVariable Long id) {
        log.debug("REST request to get Sozlesme : {}", id);
        Optional<Sozlesme> sozlesme = sozlesmeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sozlesme);
    }

    /**
     * {@code DELETE  /sozlesmes/:id} : delete the "id" sozlesme.
     *
     * @param id the id of the sozlesme to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sozlesmes/{id}")
    public ResponseEntity<Void> deleteSozlesme(@PathVariable Long id) {
        log.debug("REST request to delete Sozlesme : {}", id);
        sozlesmeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
