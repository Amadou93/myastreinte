package sn.free.myastreinte.service.impl;

import sn.free.myastreinte.service.SwitcheService;
import sn.free.myastreinte.domain.Switche;
import sn.free.myastreinte.repository.SwitcheRepository;
import sn.free.myastreinte.service.dto.SwitcheDTO;
import sn.free.myastreinte.service.mapper.SwitcheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Switche}.
 */
@Service
@Transactional
public class SwitcheServiceImpl implements SwitcheService {

    private final Logger log = LoggerFactory.getLogger(SwitcheServiceImpl.class);

    private final SwitcheRepository switcheRepository;

    private final SwitcheMapper switcheMapper;

    public SwitcheServiceImpl(SwitcheRepository switcheRepository, SwitcheMapper switcheMapper) {
        this.switcheRepository = switcheRepository;
        this.switcheMapper = switcheMapper;
    }

    /**
     * Save a switche.
     *
     * @param switcheDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SwitcheDTO save(SwitcheDTO switcheDTO) {
        log.debug("Request to save Switche : {}", switcheDTO);
        Switche switche = switcheMapper.toEntity(switcheDTO);
        switche = switcheRepository.save(switche);
        return switcheMapper.toDto(switche);
    }

    /**
     * Get all the switches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SwitcheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Switches");
        return switcheRepository.findAll(pageable)
            .map(switcheMapper::toDto);
    }


    /**
     * Get one switche by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SwitcheDTO> findOne(Long id) {
        log.debug("Request to get Switche : {}", id);
        return switcheRepository.findById(id)
            .map(switcheMapper::toDto);
    }

    /**
     * Delete the switche by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Switche : {}", id);
        switcheRepository.deleteById(id);
    }
}
