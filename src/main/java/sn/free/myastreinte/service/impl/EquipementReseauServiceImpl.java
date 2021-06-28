package sn.free.myastreinte.service.impl;

import sn.free.myastreinte.service.EquipementReseauService;
import sn.free.myastreinte.domain.EquipementReseau;
import sn.free.myastreinte.repository.EquipementReseauRepository;
import sn.free.myastreinte.service.dto.EquipementReseauDTO;
import sn.free.myastreinte.service.mapper.EquipementReseauMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EquipementReseau}.
 */
@Service
@Transactional
public class EquipementReseauServiceImpl implements EquipementReseauService {

    private final Logger log = LoggerFactory.getLogger(EquipementReseauServiceImpl.class);

    private final EquipementReseauRepository equipementReseauRepository;

    private final EquipementReseauMapper equipementReseauMapper;

    public EquipementReseauServiceImpl(EquipementReseauRepository equipementReseauRepository, EquipementReseauMapper equipementReseauMapper) {
        this.equipementReseauRepository = equipementReseauRepository;
        this.equipementReseauMapper = equipementReseauMapper;
    }

    /**
     * Save a equipementReseau.
     *
     * @param equipementReseauDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EquipementReseauDTO save(EquipementReseauDTO equipementReseauDTO) {
        log.debug("Request to save EquipementReseau : {}", equipementReseauDTO);
        EquipementReseau equipementReseau = equipementReseauMapper.toEntity(equipementReseauDTO);
        equipementReseau = equipementReseauRepository.save(equipementReseau);
        return equipementReseauMapper.toDto(equipementReseau);
    }

    /**
     * Get all the equipementReseaus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EquipementReseauDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EquipementReseaus");
        return equipementReseauRepository.findAll(pageable)
            .map(equipementReseauMapper::toDto);
    }


    /**
     * Get one equipementReseau by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EquipementReseauDTO> findOne(Long id) {
        log.debug("Request to get EquipementReseau : {}", id);
        return equipementReseauRepository.findById(id)
            .map(equipementReseauMapper::toDto);
    }

    /**
     * Delete the equipementReseau by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EquipementReseau : {}", id);
        equipementReseauRepository.deleteById(id);
    }
}
