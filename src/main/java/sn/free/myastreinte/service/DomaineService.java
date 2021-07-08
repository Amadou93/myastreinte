package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.DomaineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Domaine}.
 */
public interface DomaineService {

    /**
     * Save a domaine.
     *
     * @param domaineDTO the entity to save.
     * @return the persisted entity.
     */
    DomaineDTO save(DomaineDTO domaineDTO);

    /**
     * Get all the domaines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DomaineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" domaine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DomaineDTO> findOne(Long id);

    /**
     * Delete the "id" domaine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
