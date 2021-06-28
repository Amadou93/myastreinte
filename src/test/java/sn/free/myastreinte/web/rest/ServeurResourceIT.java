package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Serveur;
import sn.free.myastreinte.repository.ServeurRepository;
import sn.free.myastreinte.service.ServeurService;
import sn.free.myastreinte.service.dto.ServeurDTO;
import sn.free.myastreinte.service.mapper.ServeurMapper;
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
 * Integration tests for the {@Link ServeurResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class ServeurResourceIT {

    private static final String DEFAULT_NAME_SERVEUR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SERVEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CPU = "AAAAAAAAAAAAAAA";
    private static final String UPDATED_CPU = "BBBBBBBBBBBBBBB";

    private static final Long DEFAULT_MEMORY = 1L;
    private static final Long UPDATED_MEMORY = 2L;

    private static final Long DEFAULT_DISQUE = 1L;
    private static final Long UPDATED_DISQUE = 2L;

    private static final String DEFAULT_AVALIBITY = "AAAAAAAAAA";
    private static final String UPDATED_AVALIBITY = "BBBBBBBBBB";

    @Autowired
    private ServeurRepository serveurRepository;

    @Autowired
    private ServeurMapper serveurMapper;

    @Autowired
    private ServeurService serveurService;

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

    private MockMvc restServeurMockMvc;

    private Serveur serveur;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServeurResource serveurResource = new ServeurResource(serveurService);
        this.restServeurMockMvc = MockMvcBuilders.standaloneSetup(serveurResource)
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
    public static Serveur createEntity(EntityManager em) {
        Serveur serveur = new Serveur()
            .nameServeur(DEFAULT_NAME_SERVEUR)
            .cpu(DEFAULT_CPU)
            .memory(DEFAULT_MEMORY)
            .disque(DEFAULT_DISQUE)
            .avalibity(DEFAULT_AVALIBITY);
        return serveur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Serveur createUpdatedEntity(EntityManager em) {
        Serveur serveur = new Serveur()
            .nameServeur(UPDATED_NAME_SERVEUR)
            .cpu(UPDATED_CPU)
            .memory(UPDATED_MEMORY)
            .disque(UPDATED_DISQUE)
            .avalibity(UPDATED_AVALIBITY);
        return serveur;
    }

    @BeforeEach
    public void initTest() {
        serveur = createEntity(em);
    }

    @Test
    @Transactional
    public void createServeur() throws Exception {
        int databaseSizeBeforeCreate = serveurRepository.findAll().size();

        // Create the Serveur
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);
        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isCreated());

        // Validate the Serveur in the database
        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeCreate + 1);
        Serveur testServeur = serveurList.get(serveurList.size() - 1);
        assertThat(testServeur.getNameServeur()).isEqualTo(DEFAULT_NAME_SERVEUR);
        assertThat(testServeur.getCpu()).isEqualTo(DEFAULT_CPU);
        assertThat(testServeur.getMemory()).isEqualTo(DEFAULT_MEMORY);
        assertThat(testServeur.getDisque()).isEqualTo(DEFAULT_DISQUE);
        assertThat(testServeur.getAvalibity()).isEqualTo(DEFAULT_AVALIBITY);
    }

    @Test
    @Transactional
    public void createServeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serveurRepository.findAll().size();

        // Create the Serveur with an existing ID
        serveur.setId(1L);
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Serveur in the database
        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameServeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = serveurRepository.findAll().size();
        // set the field null
        serveur.setNameServeur(null);

        // Create the Serveur, which fails.
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = serveurRepository.findAll().size();
        // set the field null
        serveur.setMemory(null);

        // Create the Serveur, which fails.
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDisqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = serveurRepository.findAll().size();
        // set the field null
        serveur.setDisque(null);

        // Create the Serveur, which fails.
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvalibityIsRequired() throws Exception {
        int databaseSizeBeforeTest = serveurRepository.findAll().size();
        // set the field null
        serveur.setAvalibity(null);

        // Create the Serveur, which fails.
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        restServeurMockMvc.perform(post("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServeurs() throws Exception {
        // Initialize the database
        serveurRepository.saveAndFlush(serveur);

        // Get all the serveurList
        restServeurMockMvc.perform(get("/api/serveurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serveur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameServeur").value(hasItem(DEFAULT_NAME_SERVEUR.toString())))
            .andExpect(jsonPath("$.[*].cpu").value(hasItem(DEFAULT_CPU.toString())))
            .andExpect(jsonPath("$.[*].memory").value(hasItem(DEFAULT_MEMORY.intValue())))
            .andExpect(jsonPath("$.[*].disque").value(hasItem(DEFAULT_DISQUE.intValue())))
            .andExpect(jsonPath("$.[*].avalibity").value(hasItem(DEFAULT_AVALIBITY.toString())));
    }
    
    @Test
    @Transactional
    public void getServeur() throws Exception {
        // Initialize the database
        serveurRepository.saveAndFlush(serveur);

        // Get the serveur
        restServeurMockMvc.perform(get("/api/serveurs/{id}", serveur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serveur.getId().intValue()))
            .andExpect(jsonPath("$.nameServeur").value(DEFAULT_NAME_SERVEUR.toString()))
            .andExpect(jsonPath("$.cpu").value(DEFAULT_CPU.toString()))
            .andExpect(jsonPath("$.memory").value(DEFAULT_MEMORY.intValue()))
            .andExpect(jsonPath("$.disque").value(DEFAULT_DISQUE.intValue()))
            .andExpect(jsonPath("$.avalibity").value(DEFAULT_AVALIBITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServeur() throws Exception {
        // Get the serveur
        restServeurMockMvc.perform(get("/api/serveurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServeur() throws Exception {
        // Initialize the database
        serveurRepository.saveAndFlush(serveur);

        int databaseSizeBeforeUpdate = serveurRepository.findAll().size();

        // Update the serveur
        Serveur updatedServeur = serveurRepository.findById(serveur.getId()).get();
        // Disconnect from session so that the updates on updatedServeur are not directly saved in db
        em.detach(updatedServeur);
        updatedServeur
            .nameServeur(UPDATED_NAME_SERVEUR)
            .cpu(UPDATED_CPU)
            .memory(UPDATED_MEMORY)
            .disque(UPDATED_DISQUE)
            .avalibity(UPDATED_AVALIBITY);
        ServeurDTO serveurDTO = serveurMapper.toDto(updatedServeur);

        restServeurMockMvc.perform(put("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isOk());

        // Validate the Serveur in the database
        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeUpdate);
        Serveur testServeur = serveurList.get(serveurList.size() - 1);
        assertThat(testServeur.getNameServeur()).isEqualTo(UPDATED_NAME_SERVEUR);
        assertThat(testServeur.getCpu()).isEqualTo(UPDATED_CPU);
        assertThat(testServeur.getMemory()).isEqualTo(UPDATED_MEMORY);
        assertThat(testServeur.getDisque()).isEqualTo(UPDATED_DISQUE);
        assertThat(testServeur.getAvalibity()).isEqualTo(UPDATED_AVALIBITY);
    }

    @Test
    @Transactional
    public void updateNonExistingServeur() throws Exception {
        int databaseSizeBeforeUpdate = serveurRepository.findAll().size();

        // Create the Serveur
        ServeurDTO serveurDTO = serveurMapper.toDto(serveur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServeurMockMvc.perform(put("/api/serveurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serveurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Serveur in the database
        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServeur() throws Exception {
        // Initialize the database
        serveurRepository.saveAndFlush(serveur);

        int databaseSizeBeforeDelete = serveurRepository.findAll().size();

        // Delete the serveur
        restServeurMockMvc.perform(delete("/api/serveurs/{id}", serveur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Serveur> serveurList = serveurRepository.findAll();
        assertThat(serveurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Serveur.class);
        Serveur serveur1 = new Serveur();
        serveur1.setId(1L);
        Serveur serveur2 = new Serveur();
        serveur2.setId(serveur1.getId());
        assertThat(serveur1).isEqualTo(serveur2);
        serveur2.setId(2L);
        assertThat(serveur1).isNotEqualTo(serveur2);
        serveur1.setId(null);
        assertThat(serveur1).isNotEqualTo(serveur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServeurDTO.class);
        ServeurDTO serveurDTO1 = new ServeurDTO();
        serveurDTO1.setId(1L);
        ServeurDTO serveurDTO2 = new ServeurDTO();
        assertThat(serveurDTO1).isNotEqualTo(serveurDTO2);
        serveurDTO2.setId(serveurDTO1.getId());
        assertThat(serveurDTO1).isEqualTo(serveurDTO2);
        serveurDTO2.setId(2L);
        assertThat(serveurDTO1).isNotEqualTo(serveurDTO2);
        serveurDTO1.setId(null);
        assertThat(serveurDTO1).isNotEqualTo(serveurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(serveurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(serveurMapper.fromId(null)).isNull();
    }
}
