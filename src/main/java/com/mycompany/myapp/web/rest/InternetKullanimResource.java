package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.InternetKullanim;
import com.mycompany.myapp.repository.InternetKullanimRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.InternetKullanim}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InternetKullanimResource {

    private final Logger log = LoggerFactory.getLogger(InternetKullanimResource.class);

    private static final String ENTITY_NAME = "internetKullanim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InternetKullanimRepository internetKullanimRepository;

    public InternetKullanimResource(InternetKullanimRepository internetKullanimRepository) {
        this.internetKullanimRepository = internetKullanimRepository;
    }

    /**
     * {@code POST  /internet-kullanims} : Create a new internetKullanim.
     *
     * @param internetKullanim the internetKullanim to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new internetKullanim, or with status {@code 400 (Bad Request)} if the internetKullanim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/internet-kullanims")
    public ResponseEntity<InternetKullanim> createInternetKullanim(@Valid @RequestBody InternetKullanim internetKullanim) throws URISyntaxException {
        log.debug("REST request to save InternetKullanim : {}", internetKullanim);
        if (internetKullanim.getId() != null) {
            throw new BadRequestAlertException("A new internetKullanim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InternetKullanim result = internetKullanimRepository.save(internetKullanim);
        return ResponseEntity.created(new URI("/api/internet-kullanims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /internet-kullanims} : Updates an existing internetKullanim.
     *
     * @param internetKullanim the internetKullanim to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated internetKullanim,
     * or with status {@code 400 (Bad Request)} if the internetKullanim is not valid,
     * or with status {@code 500 (Internal Server Error)} if the internetKullanim couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/internet-kullanims")
    public ResponseEntity<InternetKullanim> updateInternetKullanim(@Valid @RequestBody InternetKullanim internetKullanim) throws URISyntaxException {
        log.debug("REST request to update InternetKullanim : {}", internetKullanim);
        if (internetKullanim.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InternetKullanim result = internetKullanimRepository.save(internetKullanim);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, internetKullanim.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /internet-kullanims} : get all the internetKullanims.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of internetKullanims in body.
     */
    @GetMapping("/internet-kullanims")
    public List<InternetKullanim> getAllInternetKullanims() {
        log.debug("REST request to get all InternetKullanims");
        return internetKullanimRepository.findAll();
    }

    /**
     * {@code GET  /internet-kullanims/:id} : get the "id" internetKullanim.
     *
     * @param id the id of the internetKullanim to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the internetKullanim, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/internet-kullanims/{id}")
    public ResponseEntity<InternetKullanim> getInternetKullanim(@PathVariable Long id) {
        log.debug("REST request to get InternetKullanim : {}", id);
        Optional<InternetKullanim> internetKullanim = internetKullanimRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(internetKullanim);
    }

    /**
     * {@code DELETE  /internet-kullanims/:id} : delete the "id" internetKullanim.
     *
     * @param id the id of the internetKullanim to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/internet-kullanims/{id}")
    public ResponseEntity<Void> deleteInternetKullanim(@PathVariable Long id) {
        log.debug("REST request to delete InternetKullanim : {}", id);
        internetKullanimRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
