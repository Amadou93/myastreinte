package sn.free.myastreinte.web.rest;

import javafx.scene.media.MediaPlayer;
import org.springframework.http.HttpStatus;
import sn.free.myastreinte.domain.enumeration.State;
import sn.free.myastreinte.service.IncidentService;
import sn.free.myastreinte.web.rest.errors.BadRequestAlertException;
import sn.free.myastreinte.service.dto.IncidentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing {@link sn.free.myastreinte.domain.Incident}.
 */
@RestController
@RequestMapping("/api")
public class IncidentResource<logger> {

    private final Logger log = LoggerFactory.getLogger(IncidentResource.class);

    private static final String ENTITY_NAME = "incident";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncidentService incidentService;

    public IncidentResource(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    /**
     * {@code POST  /incidents} : Create a new incident.
     *
     * @param incidentDTO the incidentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incidentDTO, or with status {@code 400 (Bad Request)} if the incident has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incidents")
    public ResponseEntity<IncidentDTO> createIncident(@Valid @RequestBody IncidentDTO incidentDTO) throws URISyntaxException {
        log.debug("REST request to save Incident : {}", incidentDTO);
        if (incidentDTO.getId() != null) {
            throw new BadRequestAlertException("A new incident cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncidentDTO result = incidentService.save(incidentDTO);
        return ResponseEntity.created(new URI("/api/incidents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /incidents} : Updates an existing incident.
     *
     * @param incidentDTO the incidentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incidentDTO,
     * or with status {@code 400 (Bad Request)} if the incidentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incidentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incidents")
    public ResponseEntity<IncidentDTO> updateIncident(@Valid @RequestBody IncidentDTO incidentDTO) throws URISyntaxException {
        log.debug("REST request to update Incident : {}", incidentDTO);
        if (incidentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncidentDTO result = incidentService.save(incidentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, incidentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /incidents} : get all the incidents.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incidents in body.
     */
    @GetMapping("/incidents")
    public ResponseEntity<List<IncidentDTO>> getAllIncidents(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Incidents");
        Page<IncidentDTO> page = incidentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /incidents/:id} : get the "id" incident.
     *
     * @param id the id of the incidentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incidentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incidents/{id}")
    public ResponseEntity<IncidentDTO> getIncident(@PathVariable Long id) {
        log.debug("REST request to get Incident : {}", id);
        Optional<IncidentDTO> incidentDTO = incidentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incidentDTO);
    }
    @GetMapping("/incidents/logs")
    public ResponseEntity incidentDTOS (@RequestParam(value="param1") String pram1, @RequestParam(value = "param2")String param2, @RequestParam(value = "param3") String param3, @RequestParam(value = "param4")Long param4, @RequestParam(value="param5")String param5, @RequestParam(value = "param6")String param6, @RequestParam(value = "param7")String param7, @RequestParam(value="param8")Long param8, @RequestParam(value = "param9")State param9, @RequestParam(value = "param10") Instant param10) {
       log.debug("call a incident method") ;
    /*    List<IncidentDTO> List = new ArrayList<>();*/
        IncidentDTO incidentDTO = new IncidentDTO();
        incidentDTO.setEquipementName("param1");
        incidentDTO.setAdresseIP("param2");
        incidentDTO.setComposant("param3");
        incidentDTO.setEquipeId(param4);
        incidentDTO.setResponsable("param5");
        incidentDTO.setMessage("param6");
        incidentDTO.setCriticite("param7");
        incidentDTO.setSla(param8);
        incidentDTO.setStatus(param9);
        incidentDTO.setDate(param10);
        incidentService.save(incidentDTO);
       /*List.add(incidentDTO);*/


        return new ResponseEntity<IncidentResource>(HttpStatus.OK);

    }


    /**
     * {@code DELETE  /incidents/:id} : delete the "id" incident.
     *
     * @param id the id of the incidentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incidents/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        log.debug("REST request to delete Incident : {}", id);
        incidentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
