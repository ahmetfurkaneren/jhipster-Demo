package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TelNo;
import com.mycompany.myapp.repository.TelNoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TelNo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TelNoResource {

    private final Logger log = LoggerFactory.getLogger(TelNoResource.class);

    private static final String ENTITY_NAME = "telNo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TelNoRepository telNoRepository;

    public TelNoResource(TelNoRepository telNoRepository) {
        this.telNoRepository = telNoRepository;
    }

    /**
     * {@code POST  /tel-nos} : Create a new telNo.
     *
     * @param telNo the telNo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new telNo, or with status {@code 400 (Bad Request)} if the telNo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tel-nos")
    public ResponseEntity<TelNo> createTelNo(@Valid @RequestBody TelNo telNo) throws URISyntaxException {
        log.debug("REST request to save TelNo : {}", telNo);
        if (telNo.getId() != null) {
            throw new BadRequestAlertException("A new telNo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TelNo result = telNoRepository.save(telNo);
        return ResponseEntity.created(new URI("/api/tel-nos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tel-nos} : Updates an existing telNo.
     *
     * @param telNo the telNo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated telNo,
     * or with status {@code 400 (Bad Request)} if the telNo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the telNo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tel-nos")
    public ResponseEntity<TelNo> updateTelNo(@Valid @RequestBody TelNo telNo) throws URISyntaxException {
        log.debug("REST request to update TelNo : {}", telNo);
        if (telNo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TelNo result = telNoRepository.save(telNo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, telNo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tel-nos} : get all the telNos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of telNos in body.
     */
    @GetMapping("/tel-nos")
    public List<TelNo> getAllTelNos() {
        log.debug("REST request to get all TelNos");
        return telNoRepository.findAll();
    }

    /**
     * {@code GET  /tel-nos/:id} : get the "id" telNo.
     *
     * @param id the id of the telNo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the telNo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tel-nos/{id}")
    public ResponseEntity<TelNo> getTelNo(@PathVariable Long id) {
        log.debug("REST request to get TelNo : {}", id);
        Optional<TelNo> telNo = telNoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(telNo);
    }

    /**
     * {@code DELETE  /tel-nos/:id} : delete the "id" telNo.
     *
     * @param id the id of the telNo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tel-nos/{id}")
    public ResponseEntity<Void> deleteTelNo(@PathVariable Long id) {
        log.debug("REST request to delete TelNo : {}", id);
        telNoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
