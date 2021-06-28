package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Absence;
import sn.free.myastreinte.repository.AbsenceRepository;
import sn.free.myastreinte.service.AbsenceService;
import sn.free.myastreinte.service.dto.AbsenceDTO;
import sn.free.myastreinte.service.mapper.AbsenceMapper;
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

/**
 * Integration tests for the {@Link AbsenceResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class AbsenceResourceIT {

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private AbsenceMapper absenceMapper;

    @Autowired
    private AbsenceService absenceService;

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

    private MockMvc restAbsenceMockMvc;

    private Absence absence;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AbsenceResource absenceResource = new AbsenceResource(absenceService);
        this.restAbsenceMockMvc = MockMvcBuilders.standaloneSetup(absenceResource)
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
    public static Absence createEntity(EntityManager em) {
        Absence absence = new Absence()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return absence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Absence createUpdatedEntity(EntityManager em) {
        Absence absence = new Absence()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return absence;
    }

    @BeforeEach
    public void initTest() {
        absence = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbsence() throws Exception {
        int databaseSizeBeforeCreate = absenceRepository.findAll().size();

        // Create the Absence
        AbsenceDTO absenceDTO = absenceMapper.toDto(absence);
        restAbsenceMockMvc.perform(post("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Absence in the database
        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeCreate + 1);
        Absence testAbsence = absenceList.get(absenceList.size() - 1);
        assertThat(testAbsence.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAbsence.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createAbsenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = absenceRepository.findAll().size();

        // Create the Absence with an existing ID
        absence.setId(1L);
        AbsenceDTO absenceDTO = absenceMapper.toDto(absence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbsenceMockMvc.perform(post("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Absence in the database
        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = absenceRepository.findAll().size();
        // set the field null
        absence.setStartDate(null);

        // Create the Absence, which fails.
        AbsenceDTO absenceDTO = absenceMapper.toDto(absence);

        restAbsenceMockMvc.perform(post("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isBadRequest());

        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = absenceRepository.findAll().size();
        // set the field null
        absence.setEndDate(null);

        // Create the Absence, which fails.
        AbsenceDTO absenceDTO = absenceMapper.toDto(absence);

        restAbsenceMockMvc.perform(post("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isBadRequest());

        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAbsences() throws Exception {
        // Initialize the database
        absenceRepository.saveAndFlush(absence);

        // Get all the absenceList
        restAbsenceMockMvc.perform(get("/api/absences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(absence.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAbsence() throws Exception {
        // Initialize the database
        absenceRepository.saveAndFlush(absence);

        // Get the absence
        restAbsenceMockMvc.perform(get("/api/absences/{id}", absence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(absence.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAbsence() throws Exception {
        // Get the absence
        restAbsenceMockMvc.perform(get("/api/absences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbsence() throws Exception {
        // Initialize the database
        absenceRepository.saveAndFlush(absence);

        int databaseSizeBeforeUpdate = absenceRepository.findAll().size();

        // Update the absence
        Absence updatedAbsence = absenceRepository.findById(absence.getId()).get();
        // Disconnect from session so that the updates on updatedAbsence are not directly saved in db
        em.detach(updatedAbsence);
        updatedAbsence
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        AbsenceDTO absenceDTO = absenceMapper.toDto(updatedAbsence);

        restAbsenceMockMvc.perform(put("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isOk());

        // Validate the Absence in the database
        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeUpdate);
        Absence testAbsence = absenceList.get(absenceList.size() - 1);
        assertThat(testAbsence.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAbsence.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAbsence() throws Exception {
        int databaseSizeBeforeUpdate = absenceRepository.findAll().size();

        // Create the Absence
        AbsenceDTO absenceDTO = absenceMapper.toDto(absence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbsenceMockMvc.perform(put("/api/absences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(absenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Absence in the database
        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbsence() throws Exception {
        // Initialize the database
        absenceRepository.saveAndFlush(absence);

        int databaseSizeBeforeDelete = absenceRepository.findAll().size();

        // Delete the absence
        restAbsenceMockMvc.perform(delete("/api/absences/{id}", absence.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Absence> absenceList = absenceRepository.findAll();
        assertThat(absenceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Absence.class);
        Absence absence1 = new Absence();
        absence1.setId(1L);
        Absence absence2 = new Absence();
        absence2.setId(absence1.getId());
        assertThat(absence1).isEqualTo(absence2);
        absence2.setId(2L);
        assertThat(absence1).isNotEqualTo(absence2);
        absence1.setId(null);
        assertThat(absence1).isNotEqualTo(absence2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbsenceDTO.class);
        AbsenceDTO absenceDTO1 = new AbsenceDTO();
        absenceDTO1.setId(1L);
        AbsenceDTO absenceDTO2 = new AbsenceDTO();
        assertThat(absenceDTO1).isNotEqualTo(absenceDTO2);
        absenceDTO2.setId(absenceDTO1.getId());
        assertThat(absenceDTO1).isEqualTo(absenceDTO2);
        absenceDTO2.setId(2L);
        assertThat(absenceDTO1).isNotEqualTo(absenceDTO2);
        absenceDTO1.setId(null);
        assertThat(absenceDTO1).isNotEqualTo(absenceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(absenceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(absenceMapper.fromId(null)).isNull();
    }
}
