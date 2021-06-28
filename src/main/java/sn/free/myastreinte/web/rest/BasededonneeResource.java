package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.service.BasededonneeService;
import sn.free.myastreinte.web.rest.errors.BadRequestAlertException;
import sn.free.myastreinte.service.dto.BasededonneeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link sn.free.myastreinte.domain.Basededonnee}.
 */
@RestController
@RequestMapping("/api")
public class BasededonneeResource {

    private final Logger log = LoggerFactory.getLogger(BasededonneeResource.class);

    private static final String ENTITY_NAME = "basededonnee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BasededonneeService basededonneeService;

    public BasededonneeResource(BasededonneeService basededonneeService) {
        this.basededonneeService = basededonneeService;
    }

    /**
     * {@code POST  /basededonnees} : Create a new basededonnee.
     *
     * @param basededonneeDTO the basededonneeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new basededonneeDTO, or with status {@code 400 (Bad Request)} if the basededonnee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/basededonnees")
    public ResponseEntity<BasededonneeDTO> createBasededonnee(@Valid @RequestBody BasededonneeDTO basededonneeDTO) throws URISyntaxException {
        log.debug("REST request to save Basededonnee : {}", basededonneeDTO);
        if (basededonneeDTO.getId() != null) {
            throw new BadRequestAlertException("A new basededonnee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BasededonneeDTO result = basededonneeService.save(basededonneeDTO);
        return ResponseEntity.created(new URI("/api/basededonnees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /basededonnees} : Updates an existing basededonnee.
     *
     * @param basededonneeDTO the basededonneeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated basededonneeDTO,
     * or with status {@code 400 (Bad Request)} if the basededonneeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the basededonneeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/basededonnees")
    public ResponseEntity<BasededonneeDTO> updateBasededonnee(@Valid @RequestBody BasededonneeDTO basededonneeDTO) throws URISyntaxException {
        log.debug("REST request to update Basededonnee : {}", basededonneeDTO);
        if (basededonneeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BasededonneeDTO result = basededonneeService.save(basededonneeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, basededonneeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /basededonnees} : get all the basededonnees.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of basededonnees in body.
     */
    @GetMapping("/basededonnees")
    public ResponseEntity<List<BasededonneeDTO>> getAllBasededonnees(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Basededonnees");
        Page<BasededonneeDTO> page = basededonneeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /basededonnees/:id} : get the "id" basededonnee.
     *
     * @param id the id of the basededonneeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the basededonneeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/basededonnees/{id}")
    public ResponseEntity<BasededonneeDTO> getBasededonnee(@PathVariable Long id) {
        log.debug("REST request to get Basededonnee : {}", id);
        Optional<BasededonneeDTO> basededonneeDTO = basededonneeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(basededonneeDTO);
    }

    /**
     * {@code DELETE  /basededonnees/:id} : delete the "id" basededonnee.
     *
     * @param id the id of the basededonneeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/basededonnees/{id}")
    public ResponseEntity<Void> deleteBasededonnee(@PathVariable Long id) {
        log.debug("REST request to delete Basededonnee : {}", id);
        basededonneeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
