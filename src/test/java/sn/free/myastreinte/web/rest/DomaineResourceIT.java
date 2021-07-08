package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Domaine;
import sn.free.myastreinte.repository.DomaineRepository;
import sn.free.myastreinte.service.DomaineService;
import sn.free.myastreinte.service.dto.DomaineDTO;
import sn.free.myastreinte.service.mapper.DomaineMapper;
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
 * Integration tests for the {@Link DomaineResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class DomaineResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_RESP_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_RESP_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_RESP_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_TEL_RESP_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESP_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESP_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_NOM_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_RESP_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_RESP_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_RESP_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_TEL_RESP_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESP_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESP_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_DEPART = "AAAAAAAAAA";
    private static final String UPDATED_NOM_DEPART = "BBBBBBBBBB";

    private static final String DEFAULT_RESP_DEPART = "AAAAAAAAAA";
    private static final String UPDATED_RESP_DEPART = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_RESP_DEPART = "AAAAAAAAAA";
    private static final String UPDATED_TEL_RESP_DEPART = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESP_DEPART = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESP_DEPART = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_NOM_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESP_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_RESP_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_DIRECTEUR = "AAAAAAAAAA";
    private static final String UPDATED_TEL_DIRECTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_DIRECTEUR = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_DIRECTEUR = "BBBBBBBBBB";

    @Autowired
    private DomaineRepository domaineRepository;

    @Autowired
    private DomaineMapper domaineMapper;

    @Autowired
    private DomaineService domaineService;

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

    private MockMvc restDomaineMockMvc;

    private Domaine domaine;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DomaineResource domaineResource = new DomaineResource(domaineService);
        this.restDomaineMockMvc = MockMvcBuilders.standaloneSetup(domaineResource)
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
    public static Domaine createEntity(EntityManager em) {
        Domaine domaine = new Domaine()
            .nom(DEFAULT_NOM)
            .nomService(DEFAULT_NOM_SERVICE)
            .respService(DEFAULT_RESP_SERVICE)
            .telRespService(DEFAULT_TEL_RESP_SERVICE)
            .emailRespService(DEFAULT_EMAIL_RESP_SERVICE)
            .nomDivision(DEFAULT_NOM_DIVISION)
            .respDivision(DEFAULT_RESP_DIVISION)
            .telRespDivision(DEFAULT_TEL_RESP_DIVISION)
            .emailRespDivision(DEFAULT_EMAIL_RESP_DIVISION)
            .nomDepart(DEFAULT_NOM_DEPART)
            .respDepart(DEFAULT_RESP_DEPART)
            .telRespDepart(DEFAULT_TEL_RESP_DEPART)
            .emailRespDepart(DEFAULT_EMAIL_RESP_DEPART)
            .nomDirection(DEFAULT_NOM_DIRECTION)
            .respDirection(DEFAULT_RESP_DIRECTION)
            .telDirecteur(DEFAULT_TEL_DIRECTEUR)
            .emailDirecteur(DEFAULT_EMAIL_DIRECTEUR);
        return domaine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domaine createUpdatedEntity(EntityManager em) {
        Domaine domaine = new Domaine()
            .nom(UPDATED_NOM)
            .nomService(UPDATED_NOM_SERVICE)
            .respService(UPDATED_RESP_SERVICE)
            .telRespService(UPDATED_TEL_RESP_SERVICE)
            .emailRespService(UPDATED_EMAIL_RESP_SERVICE)
            .nomDivision(UPDATED_NOM_DIVISION)
            .respDivision(UPDATED_RESP_DIVISION)
            .telRespDivision(UPDATED_TEL_RESP_DIVISION)
            .emailRespDivision(UPDATED_EMAIL_RESP_DIVISION)
            .nomDepart(UPDATED_NOM_DEPART)
            .respDepart(UPDATED_RESP_DEPART)
            .telRespDepart(UPDATED_TEL_RESP_DEPART)
            .emailRespDepart(UPDATED_EMAIL_RESP_DEPART)
            .nomDirection(UPDATED_NOM_DIRECTION)
            .respDirection(UPDATED_RESP_DIRECTION)
            .telDirecteur(UPDATED_TEL_DIRECTEUR)
            .emailDirecteur(UPDATED_EMAIL_DIRECTEUR);
        return domaine;
    }

    @BeforeEach
    public void initTest() {
        domaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createDomaine() throws Exception {
        int databaseSizeBeforeCreate = domaineRepository.findAll().size();

        // Create the Domaine
        DomaineDTO domaineDTO = domaineMapper.toDto(domaine);
        restDomaineMockMvc.perform(post("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaineDTO)))
            .andExpect(status().isCreated());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeCreate + 1);
        Domaine testDomaine = domaineList.get(domaineList.size() - 1);
        assertThat(testDomaine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDomaine.getNomService()).isEqualTo(DEFAULT_NOM_SERVICE);
        assertThat(testDomaine.getRespService()).isEqualTo(DEFAULT_RESP_SERVICE);
        assertThat(testDomaine.getTelRespService()).isEqualTo(DEFAULT_TEL_RESP_SERVICE);
        assertThat(testDomaine.getEmailRespService()).isEqualTo(DEFAULT_EMAIL_RESP_SERVICE);
        assertThat(testDomaine.getNomDivision()).isEqualTo(DEFAULT_NOM_DIVISION);
        assertThat(testDomaine.getRespDivision()).isEqualTo(DEFAULT_RESP_DIVISION);
        assertThat(testDomaine.getTelRespDivision()).isEqualTo(DEFAULT_TEL_RESP_DIVISION);
        assertThat(testDomaine.getEmailRespDivision()).isEqualTo(DEFAULT_EMAIL_RESP_DIVISION);
        assertThat(testDomaine.getNomDepart()).isEqualTo(DEFAULT_NOM_DEPART);
        assertThat(testDomaine.getRespDepart()).isEqualTo(DEFAULT_RESP_DEPART);
        assertThat(testDomaine.getTelRespDepart()).isEqualTo(DEFAULT_TEL_RESP_DEPART);
        assertThat(testDomaine.getEmailRespDepart()).isEqualTo(DEFAULT_EMAIL_RESP_DEPART);
        assertThat(testDomaine.getNomDirection()).isEqualTo(DEFAULT_NOM_DIRECTION);
        assertThat(testDomaine.getRespDirection()).isEqualTo(DEFAULT_RESP_DIRECTION);
        assertThat(testDomaine.getTelDirecteur()).isEqualTo(DEFAULT_TEL_DIRECTEUR);
        assertThat(testDomaine.getEmailDirecteur()).isEqualTo(DEFAULT_EMAIL_DIRECTEUR);
    }

    @Test
    @Transactional
    public void createDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = domaineRepository.findAll().size();

        // Create the Domaine with an existing ID
        domaine.setId(1L);
        DomaineDTO domaineDTO = domaineMapper.toDto(domaine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomaineMockMvc.perform(post("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = domaineRepository.findAll().size();
        // set the field null
        domaine.setNom(null);

        // Create the Domaine, which fails.
        DomaineDTO domaineDTO = domaineMapper.toDto(domaine);

        restDomaineMockMvc.perform(post("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaineDTO)))
            .andExpect(status().isBadRequest());

        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDomaines() throws Exception {
        // Initialize the database
        domaineRepository.saveAndFlush(domaine);

        // Get all the domaineList
        restDomaineMockMvc.perform(get("/api/domaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].nomService").value(hasItem(DEFAULT_NOM_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].respService").value(hasItem(DEFAULT_RESP_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].telRespService").value(hasItem(DEFAULT_TEL_RESP_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].emailRespService").value(hasItem(DEFAULT_EMAIL_RESP_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].nomDivision").value(hasItem(DEFAULT_NOM_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].respDivision").value(hasItem(DEFAULT_RESP_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].telRespDivision").value(hasItem(DEFAULT_TEL_RESP_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].emailRespDivision").value(hasItem(DEFAULT_EMAIL_RESP_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].nomDepart").value(hasItem(DEFAULT_NOM_DEPART.toString())))
            .andExpect(jsonPath("$.[*].respDepart").value(hasItem(DEFAULT_RESP_DEPART.toString())))
            .andExpect(jsonPath("$.[*].telRespDepart").value(hasItem(DEFAULT_TEL_RESP_DEPART.toString())))
            .andExpect(jsonPath("$.[*].emailRespDepart").value(hasItem(DEFAULT_EMAIL_RESP_DEPART.toString())))
            .andExpect(jsonPath("$.[*].nomDirection").value(hasItem(DEFAULT_NOM_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].respDirection").value(hasItem(DEFAULT_RESP_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].telDirecteur").value(hasItem(DEFAULT_TEL_DIRECTEUR.toString())))
            .andExpect(jsonPath("$.[*].emailDirecteur").value(hasItem(DEFAULT_EMAIL_DIRECTEUR.toString())));
    }
    
    @Test
    @Transactional
    public void getDomaine() throws Exception {
        // Initialize the database
        domaineRepository.saveAndFlush(domaine);

        // Get the domaine
        restDomaineMockMvc.perform(get("/api/domaines/{id}", domaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(domaine.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.nomService").value(DEFAULT_NOM_SERVICE.toString()))
            .andExpect(jsonPath("$.respService").value(DEFAULT_RESP_SERVICE.toString()))
            .andExpect(jsonPath("$.telRespService").value(DEFAULT_TEL_RESP_SERVICE.toString()))
            .andExpect(jsonPath("$.emailRespService").value(DEFAULT_EMAIL_RESP_SERVICE.toString()))
            .andExpect(jsonPath("$.nomDivision").value(DEFAULT_NOM_DIVISION.toString()))
            .andExpect(jsonPath("$.respDivision").value(DEFAULT_RESP_DIVISION.toString()))
            .andExpect(jsonPath("$.telRespDivision").value(DEFAULT_TEL_RESP_DIVISION.toString()))
            .andExpect(jsonPath("$.emailRespDivision").value(DEFAULT_EMAIL_RESP_DIVISION.toString()))
            .andExpect(jsonPath("$.nomDepart").value(DEFAULT_NOM_DEPART.toString()))
            .andExpect(jsonPath("$.respDepart").value(DEFAULT_RESP_DEPART.toString()))
            .andExpect(jsonPath("$.telRespDepart").value(DEFAULT_TEL_RESP_DEPART.toString()))
            .andExpect(jsonPath("$.emailRespDepart").value(DEFAULT_EMAIL_RESP_DEPART.toString()))
            .andExpect(jsonPath("$.nomDirection").value(DEFAULT_NOM_DIRECTION.toString()))
            .andExpect(jsonPath("$.respDirection").value(DEFAULT_RESP_DIRECTION.toString()))
            .andExpect(jsonPath("$.telDirecteur").value(DEFAULT_TEL_DIRECTEUR.toString()))
            .andExpect(jsonPath("$.emailDirecteur").value(DEFAULT_EMAIL_DIRECTEUR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDomaine() throws Exception {
        // Get the domaine
        restDomaineMockMvc.perform(get("/api/domaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDomaine() throws Exception {
        // Initialize the database
        domaineRepository.saveAndFlush(domaine);

        int databaseSizeBeforeUpdate = domaineRepository.findAll().size();

        // Update the domaine
        Domaine updatedDomaine = domaineRepository.findById(domaine.getId()).get();
        // Disconnect from session so that the updates on updatedDomaine are not directly saved in db
        em.detach(updatedDomaine);
        updatedDomaine
            .nom(UPDATED_NOM)
            .nomService(UPDATED_NOM_SERVICE)
            .respService(UPDATED_RESP_SERVICE)
            .telRespService(UPDATED_TEL_RESP_SERVICE)
            .emailRespService(UPDATED_EMAIL_RESP_SERVICE)
            .nomDivision(UPDATED_NOM_DIVISION)
            .respDivision(UPDATED_RESP_DIVISION)
            .telRespDivision(UPDATED_TEL_RESP_DIVISION)
            .emailRespDivision(UPDATED_EMAIL_RESP_DIVISION)
            .nomDepart(UPDATED_NOM_DEPART)
            .respDepart(UPDATED_RESP_DEPART)
            .telRespDepart(UPDATED_TEL_RESP_DEPART)
            .emailRespDepart(UPDATED_EMAIL_RESP_DEPART)
            .nomDirection(UPDATED_NOM_DIRECTION)
            .respDirection(UPDATED_RESP_DIRECTION)
            .telDirecteur(UPDATED_TEL_DIRECTEUR)
            .emailDirecteur(UPDATED_EMAIL_DIRECTEUR);
        DomaineDTO domaineDTO = domaineMapper.toDto(updatedDomaine);

        restDomaineMockMvc.perform(put("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaineDTO)))
            .andExpect(status().isOk());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeUpdate);
        Domaine testDomaine = domaineList.get(domaineList.size() - 1);
        assertThat(testDomaine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDomaine.getNomService()).isEqualTo(UPDATED_NOM_SERVICE);
        assertThat(testDomaine.getRespService()).isEqualTo(UPDATED_RESP_SERVICE);
        assertThat(testDomaine.getTelRespService()).isEqualTo(UPDATED_TEL_RESP_SERVICE);
        assertThat(testDomaine.getEmailRespService()).isEqualTo(UPDATED_EMAIL_RESP_SERVICE);
        assertThat(testDomaine.getNomDivision()).isEqualTo(UPDATED_NOM_DIVISION);
        assertThat(testDomaine.getRespDivision()).isEqualTo(UPDATED_RESP_DIVISION);
        assertThat(testDomaine.getTelRespDivision()).isEqualTo(UPDATED_TEL_RESP_DIVISION);
        assertThat(testDomaine.getEmailRespDivision()).isEqualTo(UPDATED_EMAIL_RESP_DIVISION);
        assertThat(testDomaine.getNomDepart()).isEqualTo(UPDATED_NOM_DEPART);
        assertThat(testDomaine.getRespDepart()).isEqualTo(UPDATED_RESP_DEPART);
        assertThat(testDomaine.getTelRespDepart()).isEqualTo(UPDATED_TEL_RESP_DEPART);
        assertThat(testDomaine.getEmailRespDepart()).isEqualTo(UPDATED_EMAIL_RESP_DEPART);
        assertThat(testDomaine.getNomDirection()).isEqualTo(UPDATED_NOM_DIRECTION);
        assertThat(testDomaine.getRespDirection()).isEqualTo(UPDATED_RESP_DIRECTION);
        assertThat(testDomaine.getTelDirecteur()).isEqualTo(UPDATED_TEL_DIRECTEUR);
        assertThat(testDomaine.getEmailDirecteur()).isEqualTo(UPDATED_EMAIL_DIRECTEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingDomaine() throws Exception {
        int databaseSizeBeforeUpdate = domaineRepository.findAll().size();

        // Create the Domaine
        DomaineDTO domaineDTO = domaineMapper.toDto(domaine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomaineMockMvc.perform(put("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDomaine() throws Exception {
        // Initialize the database
        domaineRepository.saveAndFlush(domaine);

        int databaseSizeBeforeDelete = domaineRepository.findAll().size();

        // Delete the domaine
        restDomaineMockMvc.perform(delete("/api/domaines/{id}", domaine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Domaine.class);
        Domaine domaine1 = new Domaine();
        domaine1.setId(1L);
        Domaine domaine2 = new Domaine();
        domaine2.setId(domaine1.getId());
        assertThat(domaine1).isEqualTo(domaine2);
        domaine2.setId(2L);
        assertThat(domaine1).isNotEqualTo(domaine2);
        domaine1.setId(null);
        assertThat(domaine1).isNotEqualTo(domaine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomaineDTO.class);
        DomaineDTO domaineDTO1 = new DomaineDTO();
        domaineDTO1.setId(1L);
        DomaineDTO domaineDTO2 = new DomaineDTO();
        assertThat(domaineDTO1).isNotEqualTo(domaineDTO2);
        domaineDTO2.setId(domaineDTO1.getId());
        assertThat(domaineDTO1).isEqualTo(domaineDTO2);
        domaineDTO2.setId(2L);
        assertThat(domaineDTO1).isNotEqualTo(domaineDTO2);
        domaineDTO1.setId(null);
        assertThat(domaineDTO1).isNotEqualTo(domaineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(domaineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(domaineMapper.fromId(null)).isNull();
    }
}
