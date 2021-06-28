package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.EquipeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Equipe}.
 */
public interface EquipeService {

    /**
     * Save a equipe.
     *
     * @param equipeDTO the entity to save.
     * @return the persisted entity.
     */
    EquipeDTO save(EquipeDTO equipeDTO);

    /**
     * Get all the equipes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" equipe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipeDTO> findOne(Long id);

    /**
     * Delete the "id" equipe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
