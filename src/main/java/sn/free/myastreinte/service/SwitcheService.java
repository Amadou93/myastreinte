package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.SwitcheDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Switche}.
 */
public interface SwitcheService {

    /**
     * Save a switche.
     *
     * @param switcheDTO the entity to save.
     * @return the persisted entity.
     */
    SwitcheDTO save(SwitcheDTO switcheDTO);

    /**
     * Get all the switches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SwitcheDTO> findAll(Pageable pageable);


    /**
     * Get the "id" switche.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SwitcheDTO> findOne(Long id);

    /**
     * Delete the "id" switche.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
