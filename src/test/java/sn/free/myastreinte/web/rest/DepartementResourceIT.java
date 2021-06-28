package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Departement;
import sn.free.myastreinte.repository.DepartementRepository;
import sn.free.myastreinte.service.DepartementService;
import sn.free.myastreinte.service.dto.DepartementDTO;
import sn.free.myastreinte.service.mapper.DepartementMapper;
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
 * Integration tests for the {@Link DepartementResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class DepartementResourceIT {

    private static final String DEFAULT_DEPARTEMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTEMENT_NAME = "BBBBBBBBBB";

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DepartementMapper departementMapper;

    @Autowired
    private DepartementService departementService;

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

    private MockMvc restDepartementMockMvc;

    private Departement departement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepartementResource departementResource = new DepartementResource(departementService);
        this.restDepartementMockMvc = MockMvcBuilders.standaloneSetup(departementResource)
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
    public static Departement createEntity(EntityManager em) {
        Departement departement = new Departement()
            .departementName(DEFAULT_DEPARTEMENT_NAME);
        return departement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departement createUpdatedEntity(EntityManager em) {
        Departement departement = new Departement()
            .departementName(UPDATED_DEPARTEMENT_NAME);
        return departement;
    }

    @BeforeEach
    public void initTest() {
        departement = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepartement() throws Exception {
        int databaseSizeBeforeCreate = departementRepository.findAll().size();

        // Create the Departement
        DepartementDTO departementDTO = departementMapper.toDto(departement);
        restDepartementMockMvc.perform(post("/api/departements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isCreated());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeCreate + 1);
        Departement testDepartement = departementList.get(departementList.size() - 1);
        assertThat(testDepartement.getDepartementName()).isEqualTo(DEFAULT_DEPARTEMENT_NAME);
    }

    @Test
    @Transactional
    public void createDepartementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = departementRepository.findAll().size();

        // Create the Departement with an existing ID
        departement.setId(1L);
        DepartementDTO departementDTO = departementMapper.toDto(departement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartementMockMvc.perform(post("/api/departements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDepartementNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = departementRepository.findAll().size();
        // set the field null
        departement.setDepartementName(null);

        // Create the Departement, which fails.
        DepartementDTO departementDTO = departementMapper.toDto(departement);

        restDepartementMockMvc.perform(post("/api/departements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isBadRequest());

        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDepartements() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        // Get all the departementList
        restDepartementMockMvc.perform(get("/api/departements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departement.getId().intValue())))
            .andExpect(jsonPath("$.[*].departementName").value(hasItem(DEFAULT_DEPARTEMENT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        // Get the departement
        restDepartementMockMvc.perform(get("/api/departements/{id}", departement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(departement.getId().intValue()))
            .andExpect(jsonPath("$.departementName").value(DEFAULT_DEPARTEMENT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDepartement() throws Exception {
        // Get the departement
        restDepartementMockMvc.perform(get("/api/departements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        int databaseSizeBeforeUpdate = departementRepository.findAll().size();

        // Update the departement
        Departement updatedDepartement = departementRepository.findById(departement.getId()).get();
        // Disconnect from session so that the updates on updatedDepartement are not directly saved in db
        em.detach(updatedDepartement);
        updatedDepartement
            .departementName(UPDATED_DEPARTEMENT_NAME);
        DepartementDTO departementDTO = departementMapper.toDto(updatedDepartement);

        restDepartementMockMvc.perform(put("/api/departements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isOk());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeUpdate);
        Departement testDepartement = departementList.get(departementList.size() - 1);
        assertThat(testDepartement.getDepartementName()).isEqualTo(UPDATED_DEPARTEMENT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDepartement() throws Exception {
        int databaseSizeBeforeUpdate = departementRepository.findAll().size();

        // Create the Departement
        DepartementDTO departementDTO = departementMapper.toDto(departement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartementMockMvc.perform(put("/api/departements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(departementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Departement in the database
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepartement() throws Exception {
        // Initialize the database
        departementRepository.saveAndFlush(departement);

        int databaseSizeBeforeDelete = departementRepository.findAll().size();

        // Delete the departement
        restDepartementMockMvc.perform(delete("/api/departements/{id}", departement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Departement> departementList = departementRepository.findAll();
        assertThat(departementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Departement.class);
        Departement departement1 = new Departement();
        departement1.setId(1L);
        Departement departement2 = new Departement();
        departement2.setId(departement1.getId());
        assertThat(departement1).isEqualTo(departement2);
        departement2.setId(2L);
        assertThat(departement1).isNotEqualTo(departement2);
        departement1.setId(null);
        assertThat(departement1).isNotEqualTo(departement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartementDTO.class);
        DepartementDTO departementDTO1 = new DepartementDTO();
        departementDTO1.setId(1L);
        DepartementDTO departementDTO2 = new DepartementDTO();
        assertThat(departementDTO1).isNotEqualTo(departementDTO2);
        departementDTO2.setId(departementDTO1.getId());
        assertThat(departementDTO1).isEqualTo(departementDTO2);
        departementDTO2.setId(2L);
        assertThat(departementDTO1).isNotEqualTo(departementDTO2);
        departementDTO1.setId(null);
        assertThat(departementDTO1).isNotEqualTo(departementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(departementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(departementMapper.fromId(null)).isNull();
    }
}
