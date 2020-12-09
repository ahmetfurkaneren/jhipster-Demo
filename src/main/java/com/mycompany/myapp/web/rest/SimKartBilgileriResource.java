package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SimKartBilgileri;
import com.mycompany.myapp.repository.SimKartBilgileriRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SimKartBilgileri}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SimKartBilgileriResource {

    private final Logger log = LoggerFactory.getLogger(SimKartBilgileriResource.class);

    private static final String ENTITY_NAME = "simKartBilgileri";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SimKartBilgileriRepository simKartBilgileriRepository;

    public SimKartBilgileriResource(SimKartBilgileriRepository simKartBilgileriRepository) {
        this.simKartBilgileriRepository = simKartBilgileriRepository;
    }

    /**
     * {@code POST  /sim-kart-bilgileris} : Create a new simKartBilgileri.
     *
     * @param simKartBilgileri the simKartBilgileri to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new simKartBilgileri, or with status {@code 400 (Bad Request)} if the simKartBilgileri has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sim-kart-bilgileris")
    public ResponseEntity<SimKartBilgileri> createSimKartBilgileri(@Valid @RequestBody SimKartBilgileri simKartBilgileri) throws URISyntaxException {
        log.debug("REST request to save SimKartBilgileri : {}", simKartBilgileri);
        if (simKartBilgileri.getId() != null) {
            throw new BadRequestAlertException("A new simKartBilgileri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SimKartBilgileri result = simKartBilgileriRepository.save(simKartBilgileri);
        return ResponseEntity.created(new URI("/api/sim-kart-bilgileris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sim-kart-bilgileris} : Updates an existing simKartBilgileri.
     *
     * @param simKartBilgileri the simKartBilgileri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated simKartBilgileri,
     * or with status {@code 400 (Bad Request)} if the simKartBilgileri is not valid,
     * or with status {@code 500 (Internal Server Error)} if the simKartBilgileri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sim-kart-bilgileris")
    public ResponseEntity<SimKartBilgileri> updateSimKartBilgileri(@Valid @RequestBody SimKartBilgileri simKartBilgileri) throws URISyntaxException {
        log.debug("REST request to update SimKartBilgileri : {}", simKartBilgileri);
        if (simKartBilgileri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SimKartBilgileri result = simKartBilgileriRepository.save(simKartBilgileri);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, simKartBilgileri.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sim-kart-bilgileris} : get all the simKartBilgileris.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simKartBilgileris in body.
     */
    @GetMapping("/sim-kart-bilgileris")
    public List<SimKartBilgileri> getAllSimKartBilgileris() {
        log.debug("REST request to get all SimKartBilgileris");
        return simKartBilgileriRepository.findAll();
    }

    /**
     * {@code GET  /sim-kart-bilgileris/:id} : get the "id" simKartBilgileri.
     *
     * @param id the id of the simKartBilgileri to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the simKartBilgileri, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sim-kart-bilgileris/{id}")
    public ResponseEntity<SimKartBilgileri> getSimKartBilgileri(@PathVariable Long id) {
        log.debug("REST request to get SimKartBilgileri : {}", id);
        Optional<SimKartBilgileri> simKartBilgileri = simKartBilgileriRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(simKartBilgileri);
    }

    /**
     * {@code DELETE  /sim-kart-bilgileris/:id} : delete the "id" simKartBilgileri.
     *
     * @param id the id of the simKartBilgileri to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sim-kart-bilgileris/{id}")
    public ResponseEntity<Void> deleteSimKartBilgileri(@PathVariable Long id) {
        log.debug("REST request to delete SimKartBilgileri : {}", id);
        simKartBilgileriRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
