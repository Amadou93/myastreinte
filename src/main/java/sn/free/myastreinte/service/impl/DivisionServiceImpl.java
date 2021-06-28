package sn.free.myastreinte.service.impl;

import sn.free.myastreinte.service.DivisionService;
import sn.free.myastreinte.domain.Division;
import sn.free.myastreinte.repository.DivisionRepository;
import sn.free.myastreinte.service.dto.DivisionDTO;
import sn.free.myastreinte.service.mapper.DivisionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Division}.
 */
@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {

    private final Logger log = LoggerFactory.getLogger(DivisionServiceImpl.class);

    private final DivisionRepository divisionRepository;

    private final DivisionMapper divisionMapper;

    public DivisionServiceImpl(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    /**
     * Save a division.
     *
     * @param divisionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DivisionDTO save(DivisionDTO divisionDTO) {
        log.debug("Request to save Division : {}", divisionDTO);
        Division division = divisionMapper.toEntity(divisionDTO);
        division = divisionRepository.save(division);
        return divisionMapper.toDto(division);
    }

    /**
     * Get all the divisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DivisionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Divisions");
        return divisionRepository.findAll(pageable)
            .map(divisionMapper::toDto);
    }


    /**
     * Get one division by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DivisionDTO> findOne(Long id) {
        log.debug("Request to get Division : {}", id);
        return divisionRepository.findById(id)
            .map(divisionMapper::toDto);
    }

    /**
     * Delete the division by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Division : {}", id);
        divisionRepository.deleteById(id);
    }
}
