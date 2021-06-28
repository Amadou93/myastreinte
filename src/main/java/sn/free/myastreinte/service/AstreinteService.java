package sn.free.myastreinte.service;

import sn.free.myastreinte.domain.Astreinte;
import sn.free.myastreinte.service.dto.AstreinteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.free.myastreinte.service.dto.EmployeDTO;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Astreinte}.
 */
public interface AstreinteService {



    /**
     * Save a astreinte.
     *
     * @param astreinteDTO the entity to save.
     * @return the persisted entity.
     */
    AstreinteDTO save(AstreinteDTO astreinteDTO);

    /**
     * Get all the astreintes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AstreinteDTO> findAllByOrderByNumberWeekAsc(Pageable pageable);

    /**
     * Get all the astreintes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Astreinte> findAllAstreinte(Pageable pageable);


    /**
     * Get the "id" astreinte.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AstreinteDTO> findOne(Long id);


    /**
     * Delete the "id" astreinte.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    void bulk(InputStream inputStream, String txId) throws IOException;
    List<Astreinte> findAstreinteByChaine(Long chaine);
    LocalDate week= LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, Long.parseLong("numberWeek"));


}
