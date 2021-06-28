package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.service.SwitcheService;
import sn.free.myastreinte.web.rest.errors.BadRequestAlertException;
import sn.free.myastreinte.service.dto.SwitcheDTO;

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
 * REST controller for managing {@link sn.free.myastreinte.domain.Switche}.
 */
@RestController
@RequestMapping("/api")
public class SwitcheResource {

    private final Logger log = LoggerFactory.getLogger(SwitcheResource.class);

    private static final String ENTITY_NAME = "switche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SwitcheService switcheService;

    public SwitcheResource(SwitcheService switcheService) {
        this.switcheService = switcheService;
    }

    /**
     * {@code POST  /switches} : Create a new switche.
     *
     * @param switcheDTO the switcheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new switcheDTO, or with status {@code 400 (Bad Request)} if the switche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/switches")
    public ResponseEntity<SwitcheDTO> createSwitche(@Valid @RequestBody SwitcheDTO switcheDTO) throws URISyntaxException {
        log.debug("REST request to save Switche : {}", switcheDTO);
        if (switcheDTO.getId() != null) {
            throw new BadRequestAlertException("A new switche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SwitcheDTO result = switcheService.save(switcheDTO);
        return ResponseEntity.created(new URI("/api/switches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /switches} : Updates an existing switche.
     *
     * @param switcheDTO the switcheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated switcheDTO,
     * or with status {@code 400 (Bad Request)} if the switcheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the switcheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/switches")
    public ResponseEntity<SwitcheDTO> updateSwitche(@Valid @RequestBody SwitcheDTO switcheDTO) throws URISyntaxException {
        log.debug("REST request to update Switche : {}", switcheDTO);
        if (switcheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SwitcheDTO result = switcheService.save(switcheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, switcheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /switches} : get all the switches.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of switches in body.
     */
    @GetMapping("/switches")
    public ResponseEntity<List<SwitcheDTO>> getAllSwitches(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Switches");
        Page<SwitcheDTO> page = switcheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /switches/:id} : get the "id" switche.
     *
     * @param id the id of the switcheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the switcheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/switches/{id}")
    public ResponseEntity<SwitcheDTO> getSwitche(@PathVariable Long id) {
        log.debug("REST request to get Switche : {}", id);
        Optional<SwitcheDTO> switcheDTO = switcheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(switcheDTO);
    }

    /**
     * {@code DELETE  /switches/:id} : delete the "id" switche.
     *
     * @param id the id of the switcheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/switches/{id}")
    public ResponseEntity<Void> deleteSwitche(@PathVariable Long id) {
        log.debug("REST request to delete Switche : {}", id);
        switcheService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
