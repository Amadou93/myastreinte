package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.BasededonneeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Basededonnee}.
 */
public interface BasededonneeService {

    /**
     * Save a basededonnee.
     *
     * @param basededonneeDTO the entity to save.
     * @return the persisted entity.
     */
    BasededonneeDTO save(BasededonneeDTO basededonneeDTO);

    /**
     * Get all the basededonnees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BasededonneeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" basededonnee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BasededonneeDTO> findOne(Long id);

    /**
     * Delete the "id" basededonnee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
