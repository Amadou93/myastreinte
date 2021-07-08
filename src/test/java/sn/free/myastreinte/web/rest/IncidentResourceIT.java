package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Incident;
import sn.free.myastreinte.repository.IncidentRepository;
import sn.free.myastreinte.service.IncidentService;
import sn.free.myastreinte.service.dto.IncidentDTO;
import sn.free.myastreinte.service.mapper.IncidentMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static sn.free.myastreinte.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import sn.free.myastreinte.domain.enumeration.State;
/**
 * Integration tests for the {@Link IncidentResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class IncidentResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICITE = "AAAAAAAAAA";
    private static final String UPDATED_CRITICITE = "BBBBBBBBBB";

    private static final Long DEFAULT_SLA = 1L;
    private static final Long UPDATED_SLA = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_IP = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_IP = "BBBBBBBBBB";

    private static final String DEFAULT_COMPOSANT = "AAAAAAAAAA";
    private static final String UPDATED_COMPOSANT = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final State DEFAULT_STATUS = State.UP;
    private static final State UPDATED_STATUS = State.DOWN;

    private static final String DEFAULT_EQUIPEMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EQUIPEMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private IncidentMapper incidentMapper;

    @Autowired
    private IncidentService incidentService;

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

    private MockMvc restIncidentMockMvc;

    private Incident incident;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IncidentResource incidentResource = new IncidentResource(incidentService);
        this.restIncidentMockMvc = MockMvcBuilders.standaloneSetup(incidentResource)
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
    public static Incident createEntity(EntityManager em) {
        Incident incident = new Incident()
            .date(DEFAULT_DATE)
            .type(DEFAULT_TYPE)
            .criticite(DEFAULT_CRITICITE)
            .sla(DEFAULT_SLA)
            .description(DEFAULT_DESCRIPTION)
            .adresseIP(DEFAULT_ADRESSE_IP)
            .composant(DEFAULT_COMPOSANT)
            .responsable(DEFAULT_RESPONSABLE)
            .status(DEFAULT_STATUS)
            .equipementName(DEFAULT_EQUIPEMENT_NAME)
            .message(DEFAULT_MESSAGE);
        return incident;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Incident createUpdatedEntity(EntityManager em) {
        Incident incident = new Incident()
            .date(UPDATED_DATE)
            .type(UPDATED_TYPE)
            .criticite(UPDATED_CRITICITE)
            .sla(UPDATED_SLA)
            .description(UPDATED_DESCRIPTION)
            .adresseIP(UPDATED_ADRESSE_IP)
            .composant(UPDATED_COMPOSANT)
            .responsable(UPDATED_RESPONSABLE)
            .status(UPDATED_STATUS)
            .equipementName(UPDATED_EQUIPEMENT_NAME)
            .message(UPDATED_MESSAGE);
        return incident;
    }

    @BeforeEach
    public void initTest() {
        incident = createEntity(em);
    }

    @Test
    @Transactional
    public void createIncident() throws Exception {
        int databaseSizeBeforeCreate = incidentRepository.findAll().size();

        // Create the Incident
        IncidentDTO incidentDTO = incidentMapper.toDto(incident);
        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isCreated());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeCreate + 1);
        Incident testIncident = incidentList.get(incidentList.size() - 1);
        assertThat(testIncident.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testIncident.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testIncident.getCriticite()).isEqualTo(DEFAULT_CRITICITE);
        assertThat(testIncident.getSla()).isEqualTo(DEFAULT_SLA);
        assertThat(testIncident.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIncident.getAdresseIP()).isEqualTo(DEFAULT_ADRESSE_IP);
        assertThat(testIncident.getComposant()).isEqualTo(DEFAULT_COMPOSANT);
        assertThat(testIncident.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testIncident.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIncident.getEquipementName()).isEqualTo(DEFAULT_EQUIPEMENT_NAME);
        assertThat(testIncident.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createIncidentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = incidentRepository.findAll().size();

        // Create the Incident with an existing ID
        incident.setId(1L);
        IncidentDTO incidentDTO = incidentMapper.toDto(incident);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = incidentRepository.findAll().size();
        // set the field null
        incident.setDate(null);

        // Create the Incident, which fails.
        IncidentDTO incidentDTO = incidentMapper.toDto(incident);

        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isBadRequest());

        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = incidentRepository.findAll().size();
        // set the field null
        incident.setType(null);

        // Create the Incident, which fails.
        IncidentDTO incidentDTO = incidentMapper.toDto(incident);

        restIncidentMockMvc.perform(post("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isBadRequest());

        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIncidents() throws Exception {
        // Initialize the database
        incidentRepository.saveAndFlush(incident);

        // Get all the incidentList
        restIncidentMockMvc.perform(get("/api/incidents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incident.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].criticite").value(hasItem(DEFAULT_CRITICITE.toString())))
            .andExpect(jsonPath("$.[*].sla").value(hasItem(DEFAULT_SLA.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].adresseIP").value(hasItem(DEFAULT_ADRESSE_IP.toString())))
            .andExpect(jsonPath("$.[*].composant").value(hasItem(DEFAULT_COMPOSANT.toString())))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].equipementName").value(hasItem(DEFAULT_EQUIPEMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getIncident() throws Exception {
        // Initialize the database
        incidentRepository.saveAndFlush(incident);

        // Get the incident
        restIncidentMockMvc.perform(get("/api/incidents/{id}", incident.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(incident.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.criticite").value(DEFAULT_CRITICITE.toString()))
            .andExpect(jsonPath("$.sla").value(DEFAULT_SLA.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.adresseIP").value(DEFAULT_ADRESSE_IP.toString()))
            .andExpect(jsonPath("$.composant").value(DEFAULT_COMPOSANT.toString()))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.equipementName").value(DEFAULT_EQUIPEMENT_NAME.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIncident() throws Exception {
        // Get the incident
        restIncidentMockMvc.perform(get("/api/incidents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIncident() throws Exception {
        // Initialize the database
        incidentRepository.saveAndFlush(incident);

        int databaseSizeBeforeUpdate = incidentRepository.findAll().size();

        // Update the incident
        Incident updatedIncident = incidentRepository.findById(incident.getId()).get();
        // Disconnect from session so that the updates on updatedIncident are not directly saved in db
        em.detach(updatedIncident);
        updatedIncident
            .date(UPDATED_DATE)
            .type(UPDATED_TYPE)
            .criticite(UPDATED_CRITICITE)
            .sla(UPDATED_SLA)
            .description(UPDATED_DESCRIPTION)
            .adresseIP(UPDATED_ADRESSE_IP)
            .composant(UPDATED_COMPOSANT)
            .responsable(UPDATED_RESPONSABLE)
            .status(UPDATED_STATUS)
            .equipementName(UPDATED_EQUIPEMENT_NAME)
            .message(UPDATED_MESSAGE);
        IncidentDTO incidentDTO = incidentMapper.toDto(updatedIncident);

        restIncidentMockMvc.perform(put("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isOk());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeUpdate);
        Incident testIncident = incidentList.get(incidentList.size() - 1);
        assertThat(testIncident.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testIncident.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testIncident.getCriticite()).isEqualTo(UPDATED_CRITICITE);
        assertThat(testIncident.getSla()).isEqualTo(UPDATED_SLA);
        assertThat(testIncident.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIncident.getAdresseIP()).isEqualTo(UPDATED_ADRESSE_IP);
        assertThat(testIncident.getComposant()).isEqualTo(UPDATED_COMPOSANT);
        assertThat(testIncident.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testIncident.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIncident.getEquipementName()).isEqualTo(UPDATED_EQUIPEMENT_NAME);
        assertThat(testIncident.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingIncident() throws Exception {
        int databaseSizeBeforeUpdate = incidentRepository.findAll().size();

        // Create the Incident
        IncidentDTO incidentDTO = incidentMapper.toDto(incident);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncidentMockMvc.perform(put("/api/incidents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(incidentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Incident in the database
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIncident() throws Exception {
        // Initialize the database
        incidentRepository.saveAndFlush(incident);

        int databaseSizeBeforeDelete = incidentRepository.findAll().size();

        // Delete the incident
        restIncidentMockMvc.perform(delete("/api/incidents/{id}", incident.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Incident> incidentList = incidentRepository.findAll();
        assertThat(incidentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Incident.class);
        Incident incident1 = new Incident();
        incident1.setId(1L);
        Incident incident2 = new Incident();
        incident2.setId(incident1.getId());
        assertThat(incident1).isEqualTo(incident2);
        incident2.setId(2L);
        assertThat(incident1).isNotEqualTo(incident2);
        incident1.setId(null);
        assertThat(incident1).isNotEqualTo(incident2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncidentDTO.class);
        IncidentDTO incidentDTO1 = new IncidentDTO();
        incidentDTO1.setId(1L);
        IncidentDTO incidentDTO2 = new IncidentDTO();
        assertThat(incidentDTO1).isNotEqualTo(incidentDTO2);
        incidentDTO2.setId(incidentDTO1.getId());
        assertThat(incidentDTO1).isEqualTo(incidentDTO2);
        incidentDTO2.setId(2L);
        assertThat(incidentDTO1).isNotEqualTo(incidentDTO2);
        incidentDTO1.setId(null);
        assertThat(incidentDTO1).isNotEqualTo(incidentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(incidentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(incidentMapper.fromId(null)).isNull();
    }
}
