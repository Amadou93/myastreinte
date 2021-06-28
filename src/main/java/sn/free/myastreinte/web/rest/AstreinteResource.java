package sn.free.myastreinte.web.rest;

import org.springframework.web.multipart.MultipartFile;
import sn.free.myastreinte.domain.Astreinte;
import sn.free.myastreinte.service.AstreinteService;
import sn.free.myastreinte.web.rest.errors.BadRequestAlertException;
import sn.free.myastreinte.service.dto.AstreinteDTO;

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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link sn.free.myastreinte.domain.Astreinte}.
 */
@RestController
@RequestMapping("/api")
public class AstreinteResource {

    private final Logger log = LoggerFactory.getLogger(AstreinteResource.class);

    private static final String ENTITY_NAME = "astreinte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AstreinteService astreinteService;

    public AstreinteResource(AstreinteService astreinteService) {
        this.astreinteService = astreinteService;
    }

    /**
     * {@code POST  /astreintes} : Create a new astreinte.
     *
     * @param astreinteDTO the astreinteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new astreinteDTO, or with status {@code 400 (Bad Request)} if the astreinte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/astreintes")
    public ResponseEntity<AstreinteDTO> createAstreinte(@Valid @RequestBody AstreinteDTO astreinteDTO) throws URISyntaxException {
        log.debug("REST request to save Astreinte : {}", astreinteDTO);
        if (astreinteDTO.getId() != null) {
            throw new BadRequestAlertException("A new astreinte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AstreinteDTO result = astreinteService.save(astreinteDTO);
        return ResponseEntity.created(new URI("/api/astreintes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /astreintes} : Updates an existing astreinte.
     *
     * @param astreinteDTO the astreinteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated astreinteDTO,
     * or with status {@code 400 (Bad Request)} if the astreinteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the astreinteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/astreintes")
    public ResponseEntity<AstreinteDTO> updateAstreinte(@Valid @RequestBody AstreinteDTO astreinteDTO) throws URISyntaxException {
        log.debug("REST request to update Astreinte : {}", astreinteDTO);
        if (astreinteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AstreinteDTO result = astreinteService.save(astreinteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, astreinteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /astreintes} : get all the astreintes.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of astreintes in body.
     */
    @GetMapping("/astreintes")
    public ResponseEntity<List<Astreinte>> getAllAstreintes(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Astreintes");
        Page<Astreinte> page = astreinteService.findAllAstreinte(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }

    @PostMapping("/astreintes/upload")
    public ResponseEntity bulkAstreinte(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        String txId = UUID.randomUUID().toString();
        this.log.debug(" bulking this file {} with transactionID {}", file, txId);
        this.astreinteService.bulk(file.getInputStream(), txId);
        return ResponseEntity.created(new URI("/api/astreintes/" + txId))
            .headers(HeaderUtil.createEntityCreationAlert(this.applicationName, false, ENTITY_NAME, txId))
            .build();
    }


    /**
     * {@code GET  /astreintes/:id} : get the "id" astreinte.
     *
     * @param id the id of the astreinteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astreinteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astreintes/{id}")
    public ResponseEntity<AstreinteDTO> getAstreinte(@PathVariable Long id) {
        log.debug("REST request to get Astreinte : {}", id);
        Optional<AstreinteDTO> astreinteDTO = astreinteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(astreinteDTO);
    }

    /**
     * {@code GET  /astreintes/:id} : get the "id" astreinte.
     *
     * @param id the id of the astreinteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the astreinteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/astreintes/search/{id}")
    public ResponseEntity<Astreinte> getAstreinteByChaine(@PathVariable Long id) {
        log.debug("REST request to get Astreinte : {}", id);
        List<Astreinte> astreintes = astreinteService.findAstreinteByChaine(id);
        return new ResponseEntity(astreintes, HttpStatus.OK);
    }

    /**
     * {@code DELETE  /astreintes/:id} : delete the "id" astreinte.
     *
     * @param id the id of the astreinteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/astreintes/{id}")
    public ResponseEntity<Void> deleteAstreinte(@PathVariable Long id) {
        log.debug("REST request to delete Astreinte : {}", id);
        astreinteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
