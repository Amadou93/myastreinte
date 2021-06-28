package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.IncidentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Incident}.
 */
public interface IncidentService {

    /**
     * Save a incident.
     *
     * @param incidentDTO the entity to save.
     * @return the persisted entity.
     */
    IncidentDTO save(IncidentDTO incidentDTO);

    /**
     * Get all the incidents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncidentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" incident.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncidentDTO> findOne(Long id);

    /**
     * Delete the "id" incident.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
