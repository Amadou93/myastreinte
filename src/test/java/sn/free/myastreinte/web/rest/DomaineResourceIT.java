package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Domaine;
import sn.free.myastreinte.repository.DomaineRepository;
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

    private static final String DEFAULT_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_TEL_RESPONSABLE_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_NUM_TEL_RESPONSABLE_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESPONSABLE_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESPONSABLE_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_RESPONSABLE_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_NUM_RESPONSABLE_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESPONSABLE_DIVISION = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESPONSABLE_DIVISION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE_DEPARTEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_TEL_RESPONSABLE_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TEL_RESPONSABLE_DEPARTEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RESPONSABLE_DEPARTEMENT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RESPONSABLE_DEPARTEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_TEL_DIRECTEUR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TEL_DIRECTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_DIRECTEUR = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_DIRECTEUR = "BBBBBBBBBB";

    @Autowired
    private DomaineRepository domaineRepository;

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
        final DomaineResource domaineResource = new DomaineResource(domaineRepository);
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
            .service(DEFAULT_SERVICE)
            .responsableService(DEFAULT_RESPONSABLE_SERVICE)
            .numTelResponsableService(DEFAULT_NUM_TEL_RESPONSABLE_SERVICE)
            .emailResponsableService(DEFAULT_EMAIL_RESPONSABLE_SERVICE)
            .division(DEFAULT_DIVISION)
            .responsableDivision(DEFAULT_RESPONSABLE_DIVISION)
            .numResponsableDivision(DEFAULT_NUM_RESPONSABLE_DIVISION)
            .emailResponsableDivision(DEFAULT_EMAIL_RESPONSABLE_DIVISION)
            .departement(DEFAULT_DEPARTEMENT)
            .responsableDepartement(DEFAULT_RESPONSABLE_DEPARTEMENT)
            .numeroTelResponsableDepartement(DEFAULT_NUMERO_TEL_RESPONSABLE_DEPARTEMENT)
            .emailResponsableDepartement(DEFAULT_EMAIL_RESPONSABLE_DEPARTEMENT)
            .direction(DEFAULT_DIRECTION)
            .responsableDirection(DEFAULT_RESPONSABLE_DIRECTION)
            .numeroTelDirecteur(DEFAULT_NUMERO_TEL_DIRECTEUR)
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
            .service(UPDATED_SERVICE)
            .responsableService(UPDATED_RESPONSABLE_SERVICE)
            .numTelResponsableService(UPDATED_NUM_TEL_RESPONSABLE_SERVICE)
            .emailResponsableService(UPDATED_EMAIL_RESPONSABLE_SERVICE)
            .division(UPDATED_DIVISION)
            .responsableDivision(UPDATED_RESPONSABLE_DIVISION)
            .numResponsableDivision(UPDATED_NUM_RESPONSABLE_DIVISION)
            .emailResponsableDivision(UPDATED_EMAIL_RESPONSABLE_DIVISION)
            .departement(UPDATED_DEPARTEMENT)
            .responsableDepartement(UPDATED_RESPONSABLE_DEPARTEMENT)
            .numeroTelResponsableDepartement(UPDATED_NUMERO_TEL_RESPONSABLE_DEPARTEMENT)
            .emailResponsableDepartement(UPDATED_EMAIL_RESPONSABLE_DEPARTEMENT)
            .direction(UPDATED_DIRECTION)
            .responsableDirection(UPDATED_RESPONSABLE_DIRECTION)
            .numeroTelDirecteur(UPDATED_NUMERO_TEL_DIRECTEUR)
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
        restDomaineMockMvc.perform(post("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaine)))
            .andExpect(status().isCreated());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeCreate + 1);
        Domaine testDomaine = domaineList.get(domaineList.size() - 1);
        assertThat(testDomaine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDomaine.getService()).isEqualTo(DEFAULT_SERVICE);
        assertThat(testDomaine.getResponsableService()).isEqualTo(DEFAULT_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getNumTelResponsableService()).isEqualTo(DEFAULT_NUM_TEL_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getEmailResponsableService()).isEqualTo(DEFAULT_EMAIL_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getDivision()).isEqualTo(DEFAULT_DIVISION);
        assertThat(testDomaine.getResponsableDivision()).isEqualTo(DEFAULT_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getNumResponsableDivision()).isEqualTo(DEFAULT_NUM_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getEmailResponsableDivision()).isEqualTo(DEFAULT_EMAIL_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getDepartement()).isEqualTo(DEFAULT_DEPARTEMENT);
        assertThat(testDomaine.getResponsableDepartement()).isEqualTo(DEFAULT_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getNumeroTelResponsableDepartement()).isEqualTo(DEFAULT_NUMERO_TEL_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getEmailResponsableDepartement()).isEqualTo(DEFAULT_EMAIL_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getDirection()).isEqualTo(DEFAULT_DIRECTION);
        assertThat(testDomaine.getResponsableDirection()).isEqualTo(DEFAULT_RESPONSABLE_DIRECTION);
        assertThat(testDomaine.getNumeroTelDirecteur()).isEqualTo(DEFAULT_NUMERO_TEL_DIRECTEUR);
        assertThat(testDomaine.getEmailDirecteur()).isEqualTo(DEFAULT_EMAIL_DIRECTEUR);
    }

    @Test
    @Transactional
    public void createDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = domaineRepository.findAll().size();

        // Create the Domaine with an existing ID
        domaine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomaineMockMvc.perform(post("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaine)))
            .andExpect(status().isBadRequest());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeCreate);
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
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].responsableService").value(hasItem(DEFAULT_RESPONSABLE_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].numTelResponsableService").value(hasItem(DEFAULT_NUM_TEL_RESPONSABLE_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].emailResponsableService").value(hasItem(DEFAULT_EMAIL_RESPONSABLE_SERVICE.toString())))
            .andExpect(jsonPath("$.[*].division").value(hasItem(DEFAULT_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].responsableDivision").value(hasItem(DEFAULT_RESPONSABLE_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].numResponsableDivision").value(hasItem(DEFAULT_NUM_RESPONSABLE_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].emailResponsableDivision").value(hasItem(DEFAULT_EMAIL_RESPONSABLE_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].departement").value(hasItem(DEFAULT_DEPARTEMENT.toString())))
            .andExpect(jsonPath("$.[*].responsableDepartement").value(hasItem(DEFAULT_RESPONSABLE_DEPARTEMENT.toString())))
            .andExpect(jsonPath("$.[*].numeroTelResponsableDepartement").value(hasItem(DEFAULT_NUMERO_TEL_RESPONSABLE_DEPARTEMENT.toString())))
            .andExpect(jsonPath("$.[*].emailResponsableDepartement").value(hasItem(DEFAULT_EMAIL_RESPONSABLE_DEPARTEMENT.toString())))
            .andExpect(jsonPath("$.[*].direction").value(hasItem(DEFAULT_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].responsableDirection").value(hasItem(DEFAULT_RESPONSABLE_DIRECTION.toString())))
            .andExpect(jsonPath("$.[*].numeroTelDirecteur").value(hasItem(DEFAULT_NUMERO_TEL_DIRECTEUR.toString())))
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
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE.toString()))
            .andExpect(jsonPath("$.responsableService").value(DEFAULT_RESPONSABLE_SERVICE.toString()))
            .andExpect(jsonPath("$.numTelResponsableService").value(DEFAULT_NUM_TEL_RESPONSABLE_SERVICE.toString()))
            .andExpect(jsonPath("$.emailResponsableService").value(DEFAULT_EMAIL_RESPONSABLE_SERVICE.toString()))
            .andExpect(jsonPath("$.division").value(DEFAULT_DIVISION.toString()))
            .andExpect(jsonPath("$.responsableDivision").value(DEFAULT_RESPONSABLE_DIVISION.toString()))
            .andExpect(jsonPath("$.numResponsableDivision").value(DEFAULT_NUM_RESPONSABLE_DIVISION.toString()))
            .andExpect(jsonPath("$.emailResponsableDivision").value(DEFAULT_EMAIL_RESPONSABLE_DIVISION.toString()))
            .andExpect(jsonPath("$.departement").value(DEFAULT_DEPARTEMENT.toString()))
            .andExpect(jsonPath("$.responsableDepartement").value(DEFAULT_RESPONSABLE_DEPARTEMENT.toString()))
            .andExpect(jsonPath("$.numeroTelResponsableDepartement").value(DEFAULT_NUMERO_TEL_RESPONSABLE_DEPARTEMENT.toString()))
            .andExpect(jsonPath("$.emailResponsableDepartement").value(DEFAULT_EMAIL_RESPONSABLE_DEPARTEMENT.toString()))
            .andExpect(jsonPath("$.direction").value(DEFAULT_DIRECTION.toString()))
            .andExpect(jsonPath("$.responsableDirection").value(DEFAULT_RESPONSABLE_DIRECTION.toString()))
            .andExpect(jsonPath("$.numeroTelDirecteur").value(DEFAULT_NUMERO_TEL_DIRECTEUR.toString()))
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
            .service(UPDATED_SERVICE)
            .responsableService(UPDATED_RESPONSABLE_SERVICE)
            .numTelResponsableService(UPDATED_NUM_TEL_RESPONSABLE_SERVICE)
            .emailResponsableService(UPDATED_EMAIL_RESPONSABLE_SERVICE)
            .division(UPDATED_DIVISION)
            .responsableDivision(UPDATED_RESPONSABLE_DIVISION)
            .numResponsableDivision(UPDATED_NUM_RESPONSABLE_DIVISION)
            .emailResponsableDivision(UPDATED_EMAIL_RESPONSABLE_DIVISION)
            .departement(UPDATED_DEPARTEMENT)
            .responsableDepartement(UPDATED_RESPONSABLE_DEPARTEMENT)
            .numeroTelResponsableDepartement(UPDATED_NUMERO_TEL_RESPONSABLE_DEPARTEMENT)
            .emailResponsableDepartement(UPDATED_EMAIL_RESPONSABLE_DEPARTEMENT)
            .direction(UPDATED_DIRECTION)
            .responsableDirection(UPDATED_RESPONSABLE_DIRECTION)
            .numeroTelDirecteur(UPDATED_NUMERO_TEL_DIRECTEUR)
            .emailDirecteur(UPDATED_EMAIL_DIRECTEUR);

        restDomaineMockMvc.perform(put("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDomaine)))
            .andExpect(status().isOk());

        // Validate the Domaine in the database
        List<Domaine> domaineList = domaineRepository.findAll();
        assertThat(domaineList).hasSize(databaseSizeBeforeUpdate);
        Domaine testDomaine = domaineList.get(domaineList.size() - 1);
        assertThat(testDomaine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDomaine.getService()).isEqualTo(UPDATED_SERVICE);
        assertThat(testDomaine.getResponsableService()).isEqualTo(UPDATED_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getNumTelResponsableService()).isEqualTo(UPDATED_NUM_TEL_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getEmailResponsableService()).isEqualTo(UPDATED_EMAIL_RESPONSABLE_SERVICE);
        assertThat(testDomaine.getDivision()).isEqualTo(UPDATED_DIVISION);
        assertThat(testDomaine.getResponsableDivision()).isEqualTo(UPDATED_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getNumResponsableDivision()).isEqualTo(UPDATED_NUM_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getEmailResponsableDivision()).isEqualTo(UPDATED_EMAIL_RESPONSABLE_DIVISION);
        assertThat(testDomaine.getDepartement()).isEqualTo(UPDATED_DEPARTEMENT);
        assertThat(testDomaine.getResponsableDepartement()).isEqualTo(UPDATED_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getNumeroTelResponsableDepartement()).isEqualTo(UPDATED_NUMERO_TEL_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getEmailResponsableDepartement()).isEqualTo(UPDATED_EMAIL_RESPONSABLE_DEPARTEMENT);
        assertThat(testDomaine.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testDomaine.getResponsableDirection()).isEqualTo(UPDATED_RESPONSABLE_DIRECTION);
        assertThat(testDomaine.getNumeroTelDirecteur()).isEqualTo(UPDATED_NUMERO_TEL_DIRECTEUR);
        assertThat(testDomaine.getEmailDirecteur()).isEqualTo(UPDATED_EMAIL_DIRECTEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingDomaine() throws Exception {
        int databaseSizeBeforeUpdate = domaineRepository.findAll().size();

        // Create the Domaine

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomaineMockMvc.perform(put("/api/domaines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domaine)))
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
}
