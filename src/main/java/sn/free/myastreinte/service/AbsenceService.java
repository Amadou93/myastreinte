package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.AbsenceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Absence}.
 */
public interface AbsenceService {

    /**
     * Save a absence.
     *
     * @param absenceDTO the entity to save.
     * @return the persisted entity.
     */
    AbsenceDTO save(AbsenceDTO absenceDTO);

    /**
     * Get all the absences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AbsenceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" absence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbsenceDTO> findOne(Long id);

    /**
     * Delete the "id" absence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
