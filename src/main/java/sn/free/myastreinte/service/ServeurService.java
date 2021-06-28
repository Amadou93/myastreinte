package sn.free.myastreinte.service;

import sn.free.myastreinte.service.dto.ServeurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Serveur}.
 */
public interface ServeurService {

    /**
     * Save a serveur.
     *
     * @param serveurDTO the entity to save.
     * @return the persisted entity.
     */
    ServeurDTO save(ServeurDTO serveurDTO);

    /**
     * Get all the serveurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServeurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" serveur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServeurDTO> findOne(Long id);

    /**
     * Delete the "id" serveur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
