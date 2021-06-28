package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.service.EquipementReseauService;
import sn.free.myastreinte.web.rest.errors.BadRequestAlertException;
import sn.free.myastreinte.service.dto.EquipementReseauDTO;

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
 * REST controller for managing {@link sn.free.myastreinte.domain.EquipementReseau}.
 */
@RestController
@RequestMapping("/api")
public class EquipementReseauResource {

    private final Logger log = LoggerFactory.getLogger(EquipementReseauResource.class);

    private static final String ENTITY_NAME = "equipementReseau";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EquipementReseauService equipementReseauService;

    public EquipementReseauResource(EquipementReseauService equipementReseauService) {
        this.equipementReseauService = equipementReseauService;
    }

    /**
     * {@code POST  /equipement-reseaus} : Create a new equipementReseau.
     *
     * @param equipementReseauDTO the equipementReseauDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipementReseauDTO, or with status {@code 400 (Bad Request)} if the equipementReseau has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/equipement-reseaus")
    public ResponseEntity<EquipementReseauDTO> createEquipementReseau(@Valid @RequestBody EquipementReseauDTO equipementReseauDTO) throws URISyntaxException {
        log.debug("REST request to save EquipementReseau : {}", equipementReseauDTO);
        if (equipementReseauDTO.getId() != null) {
            throw new BadRequestAlertException("A new equipementReseau cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EquipementReseauDTO result = equipementReseauService.save(equipementReseauDTO);
        return ResponseEntity.created(new URI("/api/equipement-reseaus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /equipement-reseaus} : Updates an existing equipementReseau.
     *
     * @param equipementReseauDTO the equipementReseauDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipementReseauDTO,
     * or with status {@code 400 (Bad Request)} if the equipementReseauDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipementReseauDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/equipement-reseaus")
    public ResponseEntity<EquipementReseauDTO> updateEquipementReseau(@Valid @RequestBody EquipementReseauDTO equipementReseauDTO) throws URISyntaxException {
        log.debug("REST request to update EquipementReseau : {}", equipementReseauDTO);
        if (equipementReseauDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EquipementReseauDTO result = equipementReseauService.save(equipementReseauDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipementReseauDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /equipement-reseaus} : get all the equipementReseaus.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipementReseaus in body.
     */
    @GetMapping("/equipement-reseaus")
    public ResponseEntity<List<EquipementReseauDTO>> getAllEquipementReseaus(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of EquipementReseaus");
        Page<EquipementReseauDTO> page = equipementReseauService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /equipement-reseaus/:id} : get the "id" equipementReseau.
     *
     * @param id the id of the equipementReseauDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipementReseauDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/equipement-reseaus/{id}")
    public ResponseEntity<EquipementReseauDTO> getEquipementReseau(@PathVariable Long id) {
        log.debug("REST request to get EquipementReseau : {}", id);
        Optional<EquipementReseauDTO> equipementReseauDTO = equipementReseauService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipementReseauDTO);
    }

    /**
     * {@code DELETE  /equipement-reseaus/:id} : delete the "id" equipementReseau.
     *
     * @param id the id of the equipementReseauDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/equipement-reseaus/{id}")
    public ResponseEntity<Void> deleteEquipementReseau(@PathVariable Long id) {
        log.debug("REST request to delete EquipementReseau : {}", id);
        equipementReseauService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
