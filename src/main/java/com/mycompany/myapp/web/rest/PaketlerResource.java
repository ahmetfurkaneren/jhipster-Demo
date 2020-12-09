package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Paketler;
import com.mycompany.myapp.repository.PaketlerRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Paketler}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaketlerResource {

    private final Logger log = LoggerFactory.getLogger(PaketlerResource.class);

    private static final String ENTITY_NAME = "paketler";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaketlerRepository paketlerRepository;

    public PaketlerResource(PaketlerRepository paketlerRepository) {
        this.paketlerRepository = paketlerRepository;
    }

    /**
     * {@code POST  /paketlers} : Create a new paketler.
     *
     * @param paketler the paketler to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paketler, or with status {@code 400 (Bad Request)} if the paketler has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paketlers")
    public ResponseEntity<Paketler> createPaketler(@Valid @RequestBody Paketler paketler) throws URISyntaxException {
        log.debug("REST request to save Paketler : {}", paketler);
        if (paketler.getId() != null) {
            throw new BadRequestAlertException("A new paketler cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Paketler result = paketlerRepository.save(paketler);
        return ResponseEntity.created(new URI("/api/paketlers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paketlers} : Updates an existing paketler.
     *
     * @param paketler the paketler to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paketler,
     * or with status {@code 400 (Bad Request)} if the paketler is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paketler couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paketlers")
    public ResponseEntity<Paketler> updatePaketler(@Valid @RequestBody Paketler paketler) throws URISyntaxException {
        log.debug("REST request to update Paketler : {}", paketler);
        if (paketler.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Paketler result = paketlerRepository.save(paketler);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paketler.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paketlers} : get all the paketlers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paketlers in body.
     */
    @GetMapping("/paketlers")
    public List<Paketler> getAllPaketlers() {
        log.debug("REST request to get all Paketlers");
        return paketlerRepository.findAll();
    }

    /**
     * {@code GET  /paketlers/:id} : get the "id" paketler.
     *
     * @param id the id of the paketler to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paketler, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paketlers/{id}")
    public ResponseEntity<Paketler> getPaketler(@PathVariable Long id) {
        log.debug("REST request to get Paketler : {}", id);
        Optional<Paketler> paketler = paketlerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paketler);
    }

    /**
     * {@code DELETE  /paketlers/:id} : delete the "id" paketler.
     *
     * @param id the id of the paketler to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paketlers/{id}")
    public ResponseEntity<Void> deletePaketler(@PathVariable Long id) {
        log.debug("REST request to delete Paketler : {}", id);
        paketlerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
