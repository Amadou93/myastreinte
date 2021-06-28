package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Switche;
import sn.free.myastreinte.repository.SwitcheRepository;
import sn.free.myastreinte.service.SwitcheService;
import sn.free.myastreinte.service.dto.SwitcheDTO;
import sn.free.myastreinte.service.mapper.SwitcheMapper;
import sn.free.myastreinte.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static sn.free.myastreinte.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link SwitcheResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class SwitcheResourceIT {

    private static final Long DEFAULT_IP_ADDRESS = 1L;
    private static final Long UPDATED_IP_ADDRESS = 2L;

    @Autowired
    private SwitcheRepository switcheRepository;

    @Autowired
    private SwitcheMapper switcheMapper;

    @Autowired
    private SwitcheService switcheService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSwitcheMockMvc;

    private Switche switche;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SwitcheResource switcheResource = new SwitcheResource(switcheService);
        this.restSwitcheMockMvc = MockMvcBuilders.standaloneSetup(switcheResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Switche createEntity(EntityManager em) {
        Switche switche = new Switche()
            .ipAddress(DEFAULT_IP_ADDRESS);
        return switche;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Switche createUpdatedEntity(EntityManager em) {
        Switche switche = new Switche()
            .ipAddress(UPDATED_IP_ADDRESS);
        return switche;
    }

    @BeforeEach
    public void initTest() {
        switche = createEntity(em);
    }

    @Test
    @Transactional
    public void createSwitche() throws Exception {
        int databaseSizeBeforeCreate = switcheRepository.findAll().size();

        // Create the Switche
        SwitcheDTO switcheDTO = switcheMapper.toDto(switche);
        restSwitcheMockMvc.perform(post("/api/switches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(switcheDTO)))
            .andExpect(status().isCreated());

        // Validate the Switche in the database
        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeCreate + 1);
        Switche testSwitche = switcheList.get(switcheList.size() - 1);
        assertThat(testSwitche.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
    }

    @Test
    @Transactional
    public void createSwitcheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = switcheRepository.findAll().size();

        // Create the Switche with an existing ID
        switche.setId(1L);
        SwitcheDTO switcheDTO = switcheMapper.toDto(switche);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSwitcheMockMvc.perform(post("/api/switches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(switcheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Switche in the database
        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIpAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = switcheRepository.findAll().size();
        // set the field null
        switche.setIpAddress(null);

        // Create the Switche, which fails.
        SwitcheDTO switcheDTO = switcheMapper.toDto(switche);

        restSwitcheMockMvc.perform(post("/api/switches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(switcheDTO)))
            .andExpect(status().isBadRequest());

        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSwitches() throws Exception {
        // Initialize the database
        switcheRepository.saveAndFlush(switche);

        // Get all the switcheList
        restSwitcheMockMvc.perform(get("/api/switches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(switche.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.intValue())));
    }
    
    @Test
    @Transactional
    public void getSwitche() throws Exception {
        // Initialize the database
        switcheRepository.saveAndFlush(switche);

        // Get the switche
        restSwitcheMockMvc.perform(get("/api/switches/{id}", switche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(switche.getId().intValue()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSwitche() throws Exception {
        // Get the switche
        restSwitcheMockMvc.perform(get("/api/switches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSwitche() throws Exception {
        // Initialize the database
        switcheRepository.saveAndFlush(switche);

        int databaseSizeBeforeUpdate = switcheRepository.findAll().size();

        // Update the switche
        Switche updatedSwitche = switcheRepository.findById(switche.getId()).get();
        // Disconnect from session so that the updates on updatedSwitche are not directly saved in db
        em.detach(updatedSwitche);
        updatedSwitche
            .ipAddress(UPDATED_IP_ADDRESS);
        SwitcheDTO switcheDTO = switcheMapper.toDto(updatedSwitche);

        restSwitcheMockMvc.perform(put("/api/switches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(switcheDTO)))
            .andExpect(status().isOk());

        // Validate the Switche in the database
        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeUpdate);
        Switche testSwitche = switcheList.get(switcheList.size() - 1);
        assertThat(testSwitche.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingSwitche() throws Exception {
        int databaseSizeBeforeUpdate = switcheRepository.findAll().size();

        // Create the Switche
        SwitcheDTO switcheDTO = switcheMapper.toDto(switche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSwitcheMockMvc.perform(put("/api/switches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(switcheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Switche in the database
        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSwitche() throws Exception {
        // Initialize the database
        switcheRepository.saveAndFlush(switche);

        int databaseSizeBeforeDelete = switcheRepository.findAll().size();

        // Delete the switche
        restSwitcheMockMvc.perform(delete("/api/switches/{id}", switche.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Switche> switcheList = switcheRepository.findAll();
        assertThat(switcheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Switche.class);
        Switche switche1 = new Switche();
        switche1.setId(1L);
        Switche switche2 = new Switche();
        switche2.setId(switche1.getId());
        assertThat(switche1).isEqualTo(switche2);
        switche2.setId(2L);
        assertThat(switche1).isNotEqualTo(switche2);
        switche1.setId(null);
        assertThat(switche1).isNotEqualTo(switche2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SwitcheDTO.class);
        SwitcheDTO switcheDTO1 = new SwitcheDTO();
        switcheDTO1.setId(1L);
        SwitcheDTO switcheDTO2 = new SwitcheDTO();
        assertThat(switcheDTO1).isNotEqualTo(switcheDTO2);
        switcheDTO2.setId(switcheDTO1.getId());
        assertThat(switcheDTO1).isEqualTo(switcheDTO2);
        switcheDTO2.setId(2L);
        assertThat(switcheDTO1).isNotEqualTo(switcheDTO2);
        switcheDTO1.setId(null);
        assertThat(switcheDTO1).isNotEqualTo(switcheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(switcheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(switcheMapper.fromId(null)).isNull();
    }
}
