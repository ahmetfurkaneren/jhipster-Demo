package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Musteri;
import com.mycompany.myapp.repository.MusteriRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Musteri}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MusteriResource {

    private final Logger log = LoggerFactory.getLogger(MusteriResource.class);

    private static final String ENTITY_NAME = "musteri";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MusteriRepository musteriRepository;

    public MusteriResource(MusteriRepository musteriRepository) {
        this.musteriRepository = musteriRepository;
    }

    /**
     * {@code POST  /musteris} : Create a new musteri.
     *
     * @param musteri the musteri to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new musteri, or with status {@code 400 (Bad Request)} if the musteri has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/musteris")
    public ResponseEntity<Musteri> createMusteri(@Valid @RequestBody Musteri musteri) throws URISyntaxException {
        log.debug("REST request to save Musteri : {}", musteri);
        if (musteri.getId() != null) {
            throw new BadRequestAlertException("A new musteri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Musteri result = musteriRepository.save(musteri);
        return ResponseEntity.created(new URI("/api/musteris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /musteris} : Updates an existing musteri.
     *
     * @param musteri the musteri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated musteri,
     * or with status {@code 400 (Bad Request)} if the musteri is not valid,
     * or with status {@code 500 (Internal Server Error)} if the musteri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/musteris")
    public ResponseEntity<Musteri> updateMusteri(@Valid @RequestBody Musteri musteri) throws URISyntaxException {
        log.debug("REST request to update Musteri : {}", musteri);
        if (musteri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Musteri result = musteriRepository.save(musteri);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, musteri.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /musteris} : get all the musteris.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of musteris in body.
     */
    @GetMapping("/musteris")
    public List<Musteri> getAllMusteris() {
        log.debug("REST request to get all Musteris");
        return musteriRepository.findAll();
    }

    /**
     * {@code GET  /musteris/:id} : get the "id" musteri.
     *
     * @param id the id of the musteri to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the musteri, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/musteris/{id}")
    public ResponseEntity<Musteri> getMusteri(@PathVariable Long id) {
        log.debug("REST request to get Musteri : {}", id);
        Optional<Musteri> musteri = musteriRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(musteri);
    }

    /**
     * {@code DELETE  /musteris/:id} : delete the "id" musteri.
     *
     * @param id the id of the musteri to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/musteris/{id}")
    public ResponseEntity<Void> deleteMusteri(@PathVariable Long id) {
        log.debug("REST request to delete Musteri : {}", id);
        musteriRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
