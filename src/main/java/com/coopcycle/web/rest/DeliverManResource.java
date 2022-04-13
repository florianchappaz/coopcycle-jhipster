package com.coopcycle.web.rest;

import com.coopcycle.repository.DeliverManRepository;
import com.coopcycle.service.DeliverManService;
import com.coopcycle.service.dto.DeliverManDTO;
import com.coopcycle.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.coopcycle.domain.DeliverMan}.
 */
@RestController
@RequestMapping("/api")
public class DeliverManResource {

    private final Logger log = LoggerFactory.getLogger(DeliverManResource.class);

    private static final String ENTITY_NAME = "deliverMan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliverManService deliverManService;

    private final DeliverManRepository deliverManRepository;

    public DeliverManResource(DeliverManService deliverManService, DeliverManRepository deliverManRepository) {
        this.deliverManService = deliverManService;
        this.deliverManRepository = deliverManRepository;
    }

    /**
     * {@code POST  /deliver-men} : Create a new deliverMan.
     *
     * @param deliverManDTO the deliverManDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliverManDTO, or with status {@code 400 (Bad Request)} if the deliverMan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deliver-men")
    public ResponseEntity<DeliverManDTO> createDeliverMan(@Valid @RequestBody DeliverManDTO deliverManDTO) throws URISyntaxException {
        log.debug("REST request to save DeliverMan : {}", deliverManDTO);
        if (deliverManDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliverMan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliverManDTO result = deliverManService.save(deliverManDTO);
        return ResponseEntity
            .created(new URI("/api/deliver-men/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deliver-men/:id} : Updates an existing deliverMan.
     *
     * @param id the id of the deliverManDTO to save.
     * @param deliverManDTO the deliverManDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliverManDTO,
     * or with status {@code 400 (Bad Request)} if the deliverManDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliverManDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deliver-men/{id}")
    public ResponseEntity<DeliverManDTO> updateDeliverMan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DeliverManDTO deliverManDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliverMan : {}, {}", id, deliverManDTO);
        if (deliverManDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliverManDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliverManRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliverManDTO result = deliverManService.save(deliverManDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliverManDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /deliver-men/:id} : Partial updates given fields of an existing deliverMan, field will ignore if it is null
     *
     * @param id the id of the deliverManDTO to save.
     * @param deliverManDTO the deliverManDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliverManDTO,
     * or with status {@code 400 (Bad Request)} if the deliverManDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliverManDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliverManDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/deliver-men/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeliverManDTO> partialUpdateDeliverMan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DeliverManDTO deliverManDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliverMan partially : {}, {}", id, deliverManDTO);
        if (deliverManDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliverManDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliverManRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliverManDTO> result = deliverManService.partialUpdate(deliverManDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliverManDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /deliver-men} : get all the deliverMen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliverMen in body.
     */
    @GetMapping("/deliver-men")
    public List<DeliverManDTO> getAllDeliverMen() {
        log.debug("REST request to get all DeliverMen");
        return deliverManService.findAll();
    }

    /**
     * {@code GET  /deliver-men/:id} : get the "id" deliverMan.
     *
     * @param id the id of the deliverManDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliverManDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deliver-men/{id}")
    public ResponseEntity<DeliverManDTO> getDeliverMan(@PathVariable Long id) {
        log.debug("REST request to get DeliverMan : {}", id);
        Optional<DeliverManDTO> deliverManDTO = deliverManService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliverManDTO);
    }

    /**
     * {@code DELETE  /deliver-men/:id} : delete the "id" deliverMan.
     *
     * @param id the id of the deliverManDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deliver-men/{id}")
    public ResponseEntity<Void> deleteDeliverMan(@PathVariable Long id) {
        log.debug("REST request to delete DeliverMan : {}", id);
        deliverManService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
