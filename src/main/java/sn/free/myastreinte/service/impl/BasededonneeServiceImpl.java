package sn.free.myastreinte.service.impl;

import sn.free.myastreinte.service.BasededonneeService;
import sn.free.myastreinte.domain.Basededonnee;
import sn.free.myastreinte.repository.BasededonneeRepository;
import sn.free.myastreinte.service.dto.BasededonneeDTO;
import sn.free.myastreinte.service.mapper.BasededonneeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Basededonnee}.
 */
@Service
@Transactional
public class BasededonneeServiceImpl implements BasededonneeService {

    private final Logger log = LoggerFactory.getLogger(BasededonneeServiceImpl.class);

    private final BasededonneeRepository basededonneeRepository;

    private final BasededonneeMapper basededonneeMapper;

    public BasededonneeServiceImpl(BasededonneeRepository basededonneeRepository, BasededonneeMapper basededonneeMapper) {
        this.basededonneeRepository = basededonneeRepository;
        this.basededonneeMapper = basededonneeMapper;
    }

    /**
     * Save a basededonnee.
     *
     * @param basededonneeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BasededonneeDTO save(BasededonneeDTO basededonneeDTO) {
        log.debug("Request to save Basededonnee : {}", basededonneeDTO);
        Basededonnee basededonnee = basededonneeMapper.toEntity(basededonneeDTO);
        basededonnee = basededonneeRepository.save(basededonnee);
        return basededonneeMapper.toDto(basededonnee);
    }

    /**
     * Get all the basededonnees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BasededonneeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Basededonnees");
        return basededonneeRepository.findAll(pageable)
            .map(basededonneeMapper::toDto);
    }


    /**
     * Get one basededonnee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BasededonneeDTO> findOne(Long id) {
        log.debug("Request to get Basededonnee : {}", id);
        return basededonneeRepository.findById(id)
            .map(basededonneeMapper::toDto);
    }

    /**
     * Delete the basededonnee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Basededonnee : {}", id);
        basededonneeRepository.deleteById(id);
    }
}
