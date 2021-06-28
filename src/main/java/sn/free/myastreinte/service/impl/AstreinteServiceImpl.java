package sn.free.myastreinte.service.impl;

import com.opencsv.*;

import com.opencsv.CSVReaderBuilder;

import org.springframework.security.core.context.SecurityContextHolder;
import sn.free.myastreinte.service.AstreinteService;
import sn.free.myastreinte.domain.Astreinte;
import sn.free.myastreinte.repository.AstreinteRepository;
import sn.free.myastreinte.service.dto.AstreinteDTO;
import sn.free.myastreinte.service.mapper.AstreinteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Astreinte}.
 */
@Service
@Transactional
public class AstreinteServiceImpl implements AstreinteService {

    private final Logger log = LoggerFactory.getLogger(AstreinteServiceImpl.class);

    private final AstreinteRepository astreinteRepository;

    private final AstreinteMapper astreinteMapper;
    private final CSVParser csvParser;

    public AstreinteServiceImpl(AstreinteRepository astreinteRepository, AstreinteMapper astreinteMapper) {
        this.astreinteRepository = astreinteRepository;
        this.astreinteMapper = astreinteMapper;
      /*  this.csvParser = csvParser;*/
        this.csvParser = new CSVParserBuilder().withSeparator(';').build();
    }

    /**
     * Save a astreinte.
     *
     * @param astreinteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AstreinteDTO save(AstreinteDTO astreinteDTO) {
        log.debug("Request to save Astreinte : {}", astreinteDTO);
        Astreinte astreinte = astreinteMapper.toEntity(astreinteDTO);
        astreinte = astreinteRepository.save(astreinte);
        return astreinteMapper.toDto(astreinte);
    }

    /**
     * Get all the astreintes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AstreinteDTO> findAllByOrderByNumberWeekAsc(Pageable pageable) {
        log.debug("Request to get all Astreintes");
        return astreinteRepository.findAllByOrderByNumberWeekAsc(pageable)
            .map(astreinteMapper::toDto);
    }
    @Override
    public void bulk(InputStream inputStream, String txId) throws IOException {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream)).withCSVParser(this.csvParser).build();
        List<String[]> allLine = csvReader.readAll();
        allLine.parallelStream().forEach(aLine -> this.save(AstreinteDTO.builder()
            .matricul(Long.valueOf(aLine[0]))
            .year(Long.valueOf(aLine[1]))
            .numberWeek(Long.valueOf(aLine[2]))
            .build()));

    }

    /**
     * Get all the astreintes.
     *
     * @param chaine the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Astreinte> findAstreinteByChaine(Long chaine) {
        log.debug("Request to get all Astreintes");
        return astreinteRepository.findAstreinteByChaine(chaine);
    }

    /**
         * Get one astreinte by id.
         *
         * @param id the id of the entity.
         * @return the entity.
         */
        @Override
        @Transactional(readOnly = true)
        public Optional<AstreinteDTO> findOne (Long id){
            log.debug("Request to get Astreinte : {}", id);
            return astreinteRepository.findById(id)
                .map(astreinteMapper::toDto);
        }

        /**
         * Delete the astreinte by id.
         *
         * @param id the id of the entity.
         */
        @Override
        public void delete (Long id){
            log.debug("Request to delete Astreinte : {}", id);
            astreinteRepository.deleteById(id);
        }


    /**
     * Get all the astreintes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Astreinte> findAllAstreinte(Pageable pageable) {
        log.debug("Request to get all Astreintes");
        return astreinteRepository.findAllAstreinte(pageable);
    }
    }
