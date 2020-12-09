package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SozlesmeninPaketleri;
import com.mycompany.myapp.repository.SozlesmeninPaketleriRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SozlesmeninPaketleri}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SozlesmeninPaketleriResource {

    private final Logger log = LoggerFactory.getLogger(SozlesmeninPaketleriResource.class);

    private static final String ENTITY_NAME = "sozlesmeninPaketleri";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SozlesmeninPaketleriRepository sozlesmeninPaketleriRepository;

    public SozlesmeninPaketleriResource(SozlesmeninPaketleriRepository sozlesmeninPaketleriRepository) {
        this.sozlesmeninPaketleriRepository = sozlesmeninPaketleriRepository;
    }

    /**
     * {@code POST  /sozlesmenin-paketleris} : Create a new sozlesmeninPaketleri.
     *
     * @param sozlesmeninPaketleri the sozlesmeninPaketleri to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sozlesmeninPaketleri, or with status {@code 400 (Bad Request)} if the sozlesmeninPaketleri has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sozlesmenin-paketleris")
    public ResponseEntity<SozlesmeninPaketleri> createSozlesmeninPaketleri(@Valid @RequestBody SozlesmeninPaketleri sozlesmeninPaketleri) throws URISyntaxException {
        log.debug("REST request to save SozlesmeninPaketleri : {}", sozlesmeninPaketleri);
        if (sozlesmeninPaketleri.getId() != null) {
            throw new BadRequestAlertException("A new sozlesmeninPaketleri cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SozlesmeninPaketleri result = sozlesmeninPaketleriRepository.save(sozlesmeninPaketleri);
        return ResponseEntity.created(new URI("/api/sozlesmenin-paketleris/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sozlesmenin-paketleris} : Updates an existing sozlesmeninPaketleri.
     *
     * @param sozlesmeninPaketleri the sozlesmeninPaketleri to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sozlesmeninPaketleri,
     * or with status {@code 400 (Bad Request)} if the sozlesmeninPaketleri is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sozlesmeninPaketleri couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sozlesmenin-paketleris")
    public ResponseEntity<SozlesmeninPaketleri> updateSozlesmeninPaketleri(@Valid @RequestBody SozlesmeninPaketleri sozlesmeninPaketleri) throws URISyntaxException {
        log.debug("REST request to update SozlesmeninPaketleri : {}", sozlesmeninPaketleri);
        if (sozlesmeninPaketleri.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SozlesmeninPaketleri result = sozlesmeninPaketleriRepository.save(sozlesmeninPaketleri);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sozlesmeninPaketleri.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sozlesmenin-paketleris} : get all the sozlesmeninPaketleris.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sozlesmeninPaketleris in body.
     */
    @GetMapping("/sozlesmenin-paketleris")
    public List<SozlesmeninPaketleri> getAllSozlesmeninPaketleris() {
        log.debug("REST request to get all SozlesmeninPaketleris");
        return sozlesmeninPaketleriRepository.findAll();
    }

    /**
     * {@code GET  /sozlesmenin-paketleris/:id} : get the "id" sozlesmeninPaketleri.
     *
     * @param id the id of the sozlesmeninPaketleri to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sozlesmeninPaketleri, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sozlesmenin-paketleris/{id}")
    public ResponseEntity<SozlesmeninPaketleri> getSozlesmeninPaketleri(@PathVariable Long id) {
        log.debug("REST request to get SozlesmeninPaketleri : {}", id);
        Optional<SozlesmeninPaketleri> sozlesmeninPaketleri = sozlesmeninPaketleriRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sozlesmeninPaketleri);
    }

    /**
     * {@code DELETE  /sozlesmenin-paketleris/:id} : delete the "id" sozlesmeninPaketleri.
     *
     * @param id the id of the sozlesmeninPaketleri to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sozlesmenin-paketleris/{id}")
    public ResponseEntity<Void> deleteSozlesmeninPaketleri(@PathVariable Long id) {
        log.debug("REST request to delete SozlesmeninPaketleri : {}", id);
        sozlesmeninPaketleriRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
