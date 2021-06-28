package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.EquipementReseauDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.EquipementReseau}.
 */
public interface EquipementReseauService {

    /**
     * Save a equipementReseau.
     *
     * @param equipementReseauDTO the entity to save.
     * @return the persisted entity.
     */
    EquipementReseauDTO save(EquipementReseauDTO equipementReseauDTO);

    /**
     * Get all the equipementReseaus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EquipementReseauDTO> findAll(Pageable pageable);


    /**
     * Get the "id" equipementReseau.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EquipementReseauDTO> findOne(Long id);

    /**
     * Delete the "id" equipementReseau.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
