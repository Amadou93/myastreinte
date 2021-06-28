package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Basededonnee;
import sn.free.myastreinte.repository.BasededonneeRepository;
import sn.free.myastreinte.service.BasededonneeService;
import sn.free.myastreinte.service.dto.BasededonneeDTO;
import sn.free.myastreinte.service.mapper.BasededonneeMapper;
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
 * Integration tests for the {@Link BasededonneeResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class BasededonneeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BASE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BASE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMORY = "AAAAAAAAAA";
    private static final String UPDATED_MEMORY = "BBBBBBBBBB";

    @Autowired
    private BasededonneeRepository basededonneeRepository;

    @Autowired
    private BasededonneeMapper basededonneeMapper;

    @Autowired
    private BasededonneeService basededonneeService;

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

    private MockMvc restBasededonneeMockMvc;

    private Basededonnee basededonnee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BasededonneeResource basededonneeResource = new BasededonneeResource(basededonneeService);
        this.restBasededonneeMockMvc = MockMvcBuilders.standaloneSetup(basededonneeResource)
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
    public static Basededonnee createEntity(EntityManager em) {
        Basededonnee basededonnee = new Basededonnee()
            .name(DEFAULT_NAME)
            .baseType(DEFAULT_BASE_TYPE)
            .memory(DEFAULT_MEMORY);
        return basededonnee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Basededonnee createUpdatedEntity(EntityManager em) {
        Basededonnee basededonnee = new Basededonnee()
            .name(UPDATED_NAME)
            .baseType(UPDATED_BASE_TYPE)
            .memory(UPDATED_MEMORY);
        return basededonnee;
    }

    @BeforeEach
    public void initTest() {
        basededonnee = createEntity(em);
    }

    @Test
    @Transactional
    public void createBasededonnee() throws Exception {
        int databaseSizeBeforeCreate = basededonneeRepository.findAll().size();

        // Create the Basededonnee
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);
        restBasededonneeMockMvc.perform(post("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isCreated());

        // Validate the Basededonnee in the database
        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeCreate + 1);
        Basededonnee testBasededonnee = basededonneeList.get(basededonneeList.size() - 1);
        assertThat(testBasededonnee.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBasededonnee.getBaseType()).isEqualTo(DEFAULT_BASE_TYPE);
        assertThat(testBasededonnee.getMemory()).isEqualTo(DEFAULT_MEMORY);
    }

    @Test
    @Transactional
    public void createBasededonneeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = basededonneeRepository.findAll().size();

        // Create the Basededonnee with an existing ID
        basededonnee.setId(1L);
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBasededonneeMockMvc.perform(post("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Basededonnee in the database
        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = basededonneeRepository.findAll().size();
        // set the field null
        basededonnee.setName(null);

        // Create the Basededonnee, which fails.
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);

        restBasededonneeMockMvc.perform(post("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isBadRequest());

        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaseTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = basededonneeRepository.findAll().size();
        // set the field null
        basededonnee.setBaseType(null);

        // Create the Basededonnee, which fails.
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);

        restBasededonneeMockMvc.perform(post("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isBadRequest());

        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = basededonneeRepository.findAll().size();
        // set the field null
        basededonnee.setMemory(null);

        // Create the Basededonnee, which fails.
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);

        restBasededonneeMockMvc.perform(post("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isBadRequest());

        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBasededonnees() throws Exception {
        // Initialize the database
        basededonneeRepository.saveAndFlush(basededonnee);

        // Get all the basededonneeList
        restBasededonneeMockMvc.perform(get("/api/basededonnees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(basededonnee.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].baseType").value(hasItem(DEFAULT_BASE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].memory").value(hasItem(DEFAULT_MEMORY.toString())));
    }
    
    @Test
    @Transactional
    public void getBasededonnee() throws Exception {
        // Initialize the database
        basededonneeRepository.saveAndFlush(basededonnee);

        // Get the basededonnee
        restBasededonneeMockMvc.perform(get("/api/basededonnees/{id}", basededonnee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(basededonnee.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.baseType").value(DEFAULT_BASE_TYPE.toString()))
            .andExpect(jsonPath("$.memory").value(DEFAULT_MEMORY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBasededonnee() throws Exception {
        // Get the basededonnee
        restBasededonneeMockMvc.perform(get("/api/basededonnees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBasededonnee() throws Exception {
        // Initialize the database
        basededonneeRepository.saveAndFlush(basededonnee);

        int databaseSizeBeforeUpdate = basededonneeRepository.findAll().size();

        // Update the basededonnee
        Basededonnee updatedBasededonnee = basededonneeRepository.findById(basededonnee.getId()).get();
        // Disconnect from session so that the updates on updatedBasededonnee are not directly saved in db
        em.detach(updatedBasededonnee);
        updatedBasededonnee
            .name(UPDATED_NAME)
            .baseType(UPDATED_BASE_TYPE)
            .memory(UPDATED_MEMORY);
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(updatedBasededonnee);

        restBasededonneeMockMvc.perform(put("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isOk());

        // Validate the Basededonnee in the database
        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeUpdate);
        Basededonnee testBasededonnee = basededonneeList.get(basededonneeList.size() - 1);
        assertThat(testBasededonnee.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBasededonnee.getBaseType()).isEqualTo(UPDATED_BASE_TYPE);
        assertThat(testBasededonnee.getMemory()).isEqualTo(UPDATED_MEMORY);
    }

    @Test
    @Transactional
    public void updateNonExistingBasededonnee() throws Exception {
        int databaseSizeBeforeUpdate = basededonneeRepository.findAll().size();

        // Create the Basededonnee
        BasededonneeDTO basededonneeDTO = basededonneeMapper.toDto(basededonnee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBasededonneeMockMvc.perform(put("/api/basededonnees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basededonneeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Basededonnee in the database
        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBasededonnee() throws Exception {
        // Initialize the database
        basededonneeRepository.saveAndFlush(basededonnee);

        int databaseSizeBeforeDelete = basededonneeRepository.findAll().size();

        // Delete the basededonnee
        restBasededonneeMockMvc.perform(delete("/api/basededonnees/{id}", basededonnee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Basededonnee> basededonneeList = basededonneeRepository.findAll();
        assertThat(basededonneeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Basededonnee.class);
        Basededonnee basededonnee1 = new Basededonnee();
        basededonnee1.setId(1L);
        Basededonnee basededonnee2 = new Basededonnee();
        basededonnee2.setId(basededonnee1.getId());
        assertThat(basededonnee1).isEqualTo(basededonnee2);
        basededonnee2.setId(2L);
        assertThat(basededonnee1).isNotEqualTo(basededonnee2);
        basededonnee1.setId(null);
        assertThat(basededonnee1).isNotEqualTo(basededonnee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BasededonneeDTO.class);
        BasededonneeDTO basededonneeDTO1 = new BasededonneeDTO();
        basededonneeDTO1.setId(1L);
        BasededonneeDTO basededonneeDTO2 = new BasededonneeDTO();
        assertThat(basededonneeDTO1).isNotEqualTo(basededonneeDTO2);
        basededonneeDTO2.setId(basededonneeDTO1.getId());
        assertThat(basededonneeDTO1).isEqualTo(basededonneeDTO2);
        basededonneeDTO2.setId(2L);
        assertThat(basededonneeDTO1).isNotEqualTo(basededonneeDTO2);
        basededonneeDTO1.setId(null);
        assertThat(basededonneeDTO1).isNotEqualTo(basededonneeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(basededonneeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(basededonneeMapper.fromId(null)).isNull();
    }
}
