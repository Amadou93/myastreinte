package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.EquipementReseau;
import sn.free.myastreinte.repository.EquipementReseauRepository;
import sn.free.myastreinte.service.EquipementReseauService;
import sn.free.myastreinte.service.dto.EquipementReseauDTO;
import sn.free.myastreinte.service.mapper.EquipementReseauMapper;
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
 * Integration tests for the {@Link EquipementReseauResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class EquipementReseauResourceIT {

    private static final String DEFAULT_EQUIPEMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPEMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_IP_ADDRESS = 1L;
    private static final Long UPDATED_IP_ADDRESS = 2L;

    private static final Long DEFAULT_VERSION = 1L;
    private static final Long UPDATED_VERSION = 2L;

    @Autowired
    private EquipementReseauRepository equipementReseauRepository;

    @Autowired
    private EquipementReseauMapper equipementReseauMapper;

    @Autowired
    private EquipementReseauService equipementReseauService;

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

    private MockMvc restEquipementReseauMockMvc;

    private EquipementReseau equipementReseau;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EquipementReseauResource equipementReseauResource = new EquipementReseauResource(equipementReseauService);
        this.restEquipementReseauMockMvc = MockMvcBuilders.standaloneSetup(equipementReseauResource)
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
    public static EquipementReseau createEntity(EntityManager em) {
        EquipementReseau equipementReseau = new EquipementReseau()
            .equipementName(DEFAULT_EQUIPEMENT_NAME)
            .type(DEFAULT_TYPE)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .version(DEFAULT_VERSION);
        return equipementReseau;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EquipementReseau createUpdatedEntity(EntityManager em) {
        EquipementReseau equipementReseau = new EquipementReseau()
            .equipementName(UPDATED_EQUIPEMENT_NAME)
            .type(UPDATED_TYPE)
            .ipAddress(UPDATED_IP_ADDRESS)
            .version(UPDATED_VERSION);
        return equipementReseau;
    }

    @BeforeEach
    public void initTest() {
        equipementReseau = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipementReseau() throws Exception {
        int databaseSizeBeforeCreate = equipementReseauRepository.findAll().size();

        // Create the EquipementReseau
        EquipementReseauDTO equipementReseauDTO = equipementReseauMapper.toDto(equipementReseau);
        restEquipementReseauMockMvc.perform(post("/api/equipement-reseaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementReseauDTO)))
            .andExpect(status().isCreated());

        // Validate the EquipementReseau in the database
        List<EquipementReseau> equipementReseauList = equipementReseauRepository.findAll();
        assertThat(equipementReseauList).hasSize(databaseSizeBeforeCreate + 1);
        EquipementReseau testEquipementReseau = equipementReseauList.get(equipementReseauList.size() - 1);
        assertThat(testEquipementReseau.getEquipementName()).isEqualTo(DEFAULT_EQUIPEMENT_NAME);
        assertThat(testEquipementReseau.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testEquipementReseau.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testEquipementReseau.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createEquipementReseauWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = equipementReseauRepository.findAll().size();

        // Create the EquipementReseau with an existing ID
        equipementReseau.setId(1L);
        EquipementReseauDTO equipementReseauDTO = equipementReseauMapper.toDto(equipementReseau);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipementReseauMockMvc.perform(post("/api/equipement-reseaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementReseauDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EquipementReseau in the database
        List<EquipementReseau> equipementReseauList = equipementReseauRepository.findAll();
        assertThat(equipementReseauList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEquipementReseaus() throws Exception {
        // Initialize the database
        equipementReseauRepository.saveAndFlush(equipementReseau);

        // Get all the equipementReseauList
        restEquipementReseauMockMvc.perform(get("/api/equipement-reseaus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipementReseau.getId().intValue())))
            .andExpect(jsonPath("$.[*].equipementName").value(hasItem(DEFAULT_EQUIPEMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.intValue())));
    }
    
    @Test
    @Transactional
    public void getEquipementReseau() throws Exception {
        // Initialize the database
        equipementReseauRepository.saveAndFlush(equipementReseau);

        // Get the equipementReseau
        restEquipementReseauMockMvc.perform(get("/api/equipement-reseaus/{id}", equipementReseau.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(equipementReseau.getId().intValue()))
            .andExpect(jsonPath("$.equipementName").value(DEFAULT_EQUIPEMENT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEquipementReseau() throws Exception {
        // Get the equipementReseau
        restEquipementReseauMockMvc.perform(get("/api/equipement-reseaus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipementReseau() throws Exception {
        // Initialize the database
        equipementReseauRepository.saveAndFlush(equipementReseau);

        int databaseSizeBeforeUpdate = equipementReseauRepository.findAll().size();

        // Update the equipementReseau
        EquipementReseau updatedEquipementReseau = equipementReseauRepository.findById(equipementReseau.getId()).get();
        // Disconnect from session so that the updates on updatedEquipementReseau are not directly saved in db
        em.detach(updatedEquipementReseau);
        updatedEquipementReseau
            .equipementName(UPDATED_EQUIPEMENT_NAME)
            .type(UPDATED_TYPE)
            .ipAddress(UPDATED_IP_ADDRESS)
            .version(UPDATED_VERSION);
        EquipementReseauDTO equipementReseauDTO = equipementReseauMapper.toDto(updatedEquipementReseau);

        restEquipementReseauMockMvc.perform(put("/api/equipement-reseaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementReseauDTO)))
            .andExpect(status().isOk());

        // Validate the EquipementReseau in the database
        List<EquipementReseau> equipementReseauList = equipementReseauRepository.findAll();
        assertThat(equipementReseauList).hasSize(databaseSizeBeforeUpdate);
        EquipementReseau testEquipementReseau = equipementReseauList.get(equipementReseauList.size() - 1);
        assertThat(testEquipementReseau.getEquipementName()).isEqualTo(UPDATED_EQUIPEMENT_NAME);
        assertThat(testEquipementReseau.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testEquipementReseau.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testEquipementReseau.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingEquipementReseau() throws Exception {
        int databaseSizeBeforeUpdate = equipementReseauRepository.findAll().size();

        // Create the EquipementReseau
        EquipementReseauDTO equipementReseauDTO = equipementReseauMapper.toDto(equipementReseau);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipementReseauMockMvc.perform(put("/api/equipement-reseaus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(equipementReseauDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EquipementReseau in the database
        List<EquipementReseau> equipementReseauList = equipementReseauRepository.findAll();
        assertThat(equipementReseauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEquipementReseau() throws Exception {
        // Initialize the database
        equipementReseauRepository.saveAndFlush(equipementReseau);

        int databaseSizeBeforeDelete = equipementReseauRepository.findAll().size();

        // Delete the equipementReseau
        restEquipementReseauMockMvc.perform(delete("/api/equipement-reseaus/{id}", equipementReseau.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EquipementReseau> equipementReseauList = equipementReseauRepository.findAll();
        assertThat(equipementReseauList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EquipementReseau.class);
        EquipementReseau equipementReseau1 = new EquipementReseau();
        equipementReseau1.setId(1L);
        EquipementReseau equipementReseau2 = new EquipementReseau();
        equipementReseau2.setId(equipementReseau1.getId());
        assertThat(equipementReseau1).isEqualTo(equipementReseau2);
        equipementReseau2.setId(2L);
        assertThat(equipementReseau1).isNotEqualTo(equipementReseau2);
        equipementReseau1.setId(null);
        assertThat(equipementReseau1).isNotEqualTo(equipementReseau2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EquipementReseauDTO.class);
        EquipementReseauDTO equipementReseauDTO1 = new EquipementReseauDTO();
        equipementReseauDTO1.setId(1L);
        EquipementReseauDTO equipementReseauDTO2 = new EquipementReseauDTO();
        assertThat(equipementReseauDTO1).isNotEqualTo(equipementReseauDTO2);
        equipementReseauDTO2.setId(equipementReseauDTO1.getId());
        assertThat(equipementReseauDTO1).isEqualTo(equipementReseauDTO2);
        equipementReseauDTO2.setId(2L);
        assertThat(equipementReseauDTO1).isNotEqualTo(equipementReseauDTO2);
        equipementReseauDTO1.setId(null);
        assertThat(equipementReseauDTO1).isNotEqualTo(equipementReseauDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(equipementReseauMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(equipementReseauMapper.fromId(null)).isNull();
    }
}
