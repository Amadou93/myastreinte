package sn.free.myastreinte.web.rest;

import sn.free.myastreinte.MyAstreinteApp;
import sn.free.myastreinte.domain.Astreinte;
import sn.free.myastreinte.repository.AstreinteRepository;
import sn.free.myastreinte.service.AstreinteService;
import sn.free.myastreinte.service.dto.AstreinteDTO;
import sn.free.myastreinte.service.mapper.AstreinteMapper;
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
 * Integration tests for the {@Link AstreinteResource} REST controller.
 */
@SpringBootTest(classes = MyAstreinteApp.class)
public class AstreinteResourceIT {

    private static final Long DEFAULT_MATRICUL = 1L;
    private static final Long UPDATED_MATRICUL = 2L;

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final Long DEFAULT_NUMBER_WEEK = 1L;
    private static final Long UPDATED_NUMBER_WEEK = 2L;

    @Autowired
    private AstreinteRepository astreinteRepository;

    @Autowired
    private AstreinteMapper astreinteMapper;

    @Autowired
    private AstreinteService astreinteService;

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

    private MockMvc restAstreinteMockMvc;

    private Astreinte astreinte;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AstreinteResource astreinteResource = new AstreinteResource(astreinteService);
        this.restAstreinteMockMvc = MockMvcBuilders.standaloneSetup(astreinteResource)
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
    public static Astreinte createEntity(EntityManager em) {
        Astreinte astreinte = new Astreinte()
            .matricul(DEFAULT_MATRICUL)
            .year(DEFAULT_YEAR)
            .numberWeek(DEFAULT_NUMBER_WEEK);
        return astreinte;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Astreinte createUpdatedEntity(EntityManager em) {
        Astreinte astreinte = new Astreinte()
            .matricul(UPDATED_MATRICUL)
            .year(UPDATED_YEAR)
            .numberWeek(UPDATED_NUMBER_WEEK);
        return astreinte;
    }

    @BeforeEach
    public void initTest() {
        astreinte = createEntity(em);
    }

    @Test
    @Transactional
    public void createAstreinte() throws Exception {
        int databaseSizeBeforeCreate = astreinteRepository.findAll().size();

        // Create the Astreinte
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(astreinte);
        restAstreinteMockMvc.perform(post("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isCreated());

        // Validate the Astreinte in the database
        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeCreate + 1);
        Astreinte testAstreinte = astreinteList.get(astreinteList.size() - 1);
        assertThat(testAstreinte.getMatricul()).isEqualTo(DEFAULT_MATRICUL);
        assertThat(testAstreinte.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testAstreinte.getNumberWeek()).isEqualTo(DEFAULT_NUMBER_WEEK);
    }

    @Test
    @Transactional
    public void createAstreinteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = astreinteRepository.findAll().size();

        // Create the Astreinte with an existing ID
        astreinte.setId(1L);
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(astreinte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAstreinteMockMvc.perform(post("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Astreinte in the database
        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculIsRequired() throws Exception {
        int databaseSizeBeforeTest = astreinteRepository.findAll().size();
        // set the field null
        astreinte.setMatricul(null);

        // Create the Astreinte, which fails.
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(astreinte);

        restAstreinteMockMvc.perform(post("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isBadRequest());

        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberWeekIsRequired() throws Exception {
        int databaseSizeBeforeTest = astreinteRepository.findAll().size();
        // set the field null
        astreinte.setNumberWeek(null);

        // Create the Astreinte, which fails.
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(astreinte);

        restAstreinteMockMvc.perform(post("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isBadRequest());

        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAstreintes() throws Exception {
        // Initialize the database
        astreinteRepository.saveAndFlush(astreinte);

        // Get all the astreinteList
        restAstreinteMockMvc.perform(get("/api/astreintes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(astreinte.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricul").value(hasItem(DEFAULT_MATRICUL.intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].numberWeek").value(hasItem(DEFAULT_NUMBER_WEEK.intValue())));
    }

    @Test
    @Transactional
    public void getAstreinte() throws Exception {
        // Initialize the database
        astreinteRepository.saveAndFlush(astreinte);

        // Get the astreinte
        restAstreinteMockMvc.perform(get("/api/astreintes/{id}", astreinte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(astreinte.getId().intValue()))
            .andExpect(jsonPath("$.matricul").value(DEFAULT_MATRICUL.intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.numberWeek").value(DEFAULT_NUMBER_WEEK.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAstreinte() throws Exception {
        // Get the astreinte
        restAstreinteMockMvc.perform(get("/api/astreintes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAstreinte() throws Exception {
        // Initialize the database
        astreinteRepository.saveAndFlush(astreinte);

        int databaseSizeBeforeUpdate = astreinteRepository.findAll().size();

        // Update the astreinte
        Astreinte updatedAstreinte = astreinteRepository.findById(astreinte.getId()).get();
        // Disconnect from session so that the updates on updatedAstreinte are not directly saved in db
        em.detach(updatedAstreinte);
        updatedAstreinte
            .matricul(UPDATED_MATRICUL)
            .year(UPDATED_YEAR)
            .numberWeek(UPDATED_NUMBER_WEEK);
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(updatedAstreinte);

        restAstreinteMockMvc.perform(put("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isOk());

        // Validate the Astreinte in the database
        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeUpdate);
        Astreinte testAstreinte = astreinteList.get(astreinteList.size() - 1);
        assertThat(testAstreinte.getMatricul()).isEqualTo(UPDATED_MATRICUL);
        assertThat(testAstreinte.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testAstreinte.getNumberWeek()).isEqualTo(UPDATED_NUMBER_WEEK);
    }

    @Test
    @Transactional
    public void updateNonExistingAstreinte() throws Exception {
        int databaseSizeBeforeUpdate = astreinteRepository.findAll().size();

        // Create the Astreinte
        AstreinteDTO astreinteDTO = astreinteMapper.toDto(astreinte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAstreinteMockMvc.perform(put("/api/astreintes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(astreinteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Astreinte in the database
        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAstreinte() throws Exception {
        // Initialize the database
        astreinteRepository.saveAndFlush(astreinte);

        int databaseSizeBeforeDelete = astreinteRepository.findAll().size();

        // Delete the astreinte
        restAstreinteMockMvc.perform(delete("/api/astreintes/{id}", astreinte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Astreinte> astreinteList = astreinteRepository.findAll();
        assertThat(astreinteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Astreinte.class);
        Astreinte astreinte1 = new Astreinte();
        astreinte1.setId(1L);
        Astreinte astreinte2 = new Astreinte();
        astreinte2.setId(astreinte1.getId());
        assertThat(astreinte1).isEqualTo(astreinte2);
        astreinte2.setId(2L);
        assertThat(astreinte1).isNotEqualTo(astreinte2);
        astreinte1.setId(null);
        assertThat(astreinte1).isNotEqualTo(astreinte2);
    }

    /*@Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AstreinteDTO.class);
        AstreinteDTO astreinteDTO1 = new AstreinteDTO();
        astreinteDTO1.setId(1L);
       AstreinteDTO astreinteDTO2 = new AstreinteDTO();
        assertThat(astreinteDTO1).isNotEqualTo(astreinteDTO2);
        astreinteDTO2.setId(astreinteDTO1.getId());
        assertThat(astreinteDTO1).isEqualTo(astreinteDTO2);
        astreinteDTO2.setId(2L);
        assertThat(astreinteDTO1).isNotEqualTo(astreinteDTO2);
        astreinteDTO1.setId(null);
        assertThat(astreinteDTO1).isNotEqualTo(astreinteDTO2);
    }
*/
    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(astreinteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(astreinteMapper.fromId(null)).isNull();
    }
}
