package com.coopcycle.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coopcycle.IntegrationTest;
import com.coopcycle.domain.DeliverMan;
import com.coopcycle.repository.DeliverManRepository;
import com.coopcycle.service.dto.DeliverManDTO;
import com.coopcycle.service.mapper.DeliverManMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link DeliverManResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliverManResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 18;
    private static final Integer UPDATED_AGE = 19;

    private static final byte[] DEFAULT_PROFILE_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROFILE_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROFILE_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROFILE_PICTURE_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/deliver-men";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliverManRepository deliverManRepository;

    @Autowired
    private DeliverManMapper deliverManMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliverManMockMvc;

    private DeliverMan deliverMan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliverMan createEntity(EntityManager em) {
        DeliverMan deliverMan = new DeliverMan()
            .name(DEFAULT_NAME)
            .age(DEFAULT_AGE)
            .profilePicture(DEFAULT_PROFILE_PICTURE)
            .profilePictureContentType(DEFAULT_PROFILE_PICTURE_CONTENT_TYPE);
        return deliverMan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliverMan createUpdatedEntity(EntityManager em) {
        DeliverMan deliverMan = new DeliverMan()
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .profilePicture(UPDATED_PROFILE_PICTURE)
            .profilePictureContentType(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);
        return deliverMan;
    }

    @BeforeEach
    public void initTest() {
        deliverMan = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliverMan() throws Exception {
        int databaseSizeBeforeCreate = deliverManRepository.findAll().size();
        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);
        restDeliverManMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliverManDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeCreate + 1);
        DeliverMan testDeliverMan = deliverManList.get(deliverManList.size() - 1);
        assertThat(testDeliverMan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDeliverMan.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testDeliverMan.getProfilePicture()).isEqualTo(DEFAULT_PROFILE_PICTURE);
        assertThat(testDeliverMan.getProfilePictureContentType()).isEqualTo(DEFAULT_PROFILE_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createDeliverManWithExistingId() throws Exception {
        // Create the DeliverMan with an existing ID
        deliverMan.setId(1L);
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        int databaseSizeBeforeCreate = deliverManRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliverManMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliverManDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliverManRepository.findAll().size();
        // set the field null
        deliverMan.setName(null);

        // Create the DeliverMan, which fails.
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        restDeliverManMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliverManDTO)))
            .andExpect(status().isBadRequest());

        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliverManRepository.findAll().size();
        // set the field null
        deliverMan.setAge(null);

        // Create the DeliverMan, which fails.
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        restDeliverManMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliverManDTO)))
            .andExpect(status().isBadRequest());

        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDeliverMen() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        // Get all the deliverManList
        restDeliverManMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliverMan.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].profilePictureContentType").value(hasItem(DEFAULT_PROFILE_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].profilePicture").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROFILE_PICTURE))));
    }

    @Test
    @Transactional
    void getDeliverMan() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        // Get the deliverMan
        restDeliverManMockMvc
            .perform(get(ENTITY_API_URL_ID, deliverMan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliverMan.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.profilePictureContentType").value(DEFAULT_PROFILE_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.profilePicture").value(Base64Utils.encodeToString(DEFAULT_PROFILE_PICTURE)));
    }

    @Test
    @Transactional
    void getNonExistingDeliverMan() throws Exception {
        // Get the deliverMan
        restDeliverManMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliverMan() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();

        // Update the deliverMan
        DeliverMan updatedDeliverMan = deliverManRepository.findById(deliverMan.getId()).get();
        // Disconnect from session so that the updates on updatedDeliverMan are not directly saved in db
        em.detach(updatedDeliverMan);
        updatedDeliverMan
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .profilePicture(UPDATED_PROFILE_PICTURE)
            .profilePictureContentType(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(updatedDeliverMan);

        restDeliverManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliverManDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
        DeliverMan testDeliverMan = deliverManList.get(deliverManList.size() - 1);
        assertThat(testDeliverMan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDeliverMan.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testDeliverMan.getProfilePicture()).isEqualTo(UPDATED_PROFILE_PICTURE);
        assertThat(testDeliverMan.getProfilePictureContentType()).isEqualTo(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliverManDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliverManDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliverManWithPatch() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();

        // Update the deliverMan using partial update
        DeliverMan partialUpdatedDeliverMan = new DeliverMan();
        partialUpdatedDeliverMan.setId(deliverMan.getId());

        partialUpdatedDeliverMan
            .age(UPDATED_AGE)
            .profilePicture(UPDATED_PROFILE_PICTURE)
            .profilePictureContentType(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);

        restDeliverManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliverMan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliverMan))
            )
            .andExpect(status().isOk());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
        DeliverMan testDeliverMan = deliverManList.get(deliverManList.size() - 1);
        assertThat(testDeliverMan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDeliverMan.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testDeliverMan.getProfilePicture()).isEqualTo(UPDATED_PROFILE_PICTURE);
        assertThat(testDeliverMan.getProfilePictureContentType()).isEqualTo(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateDeliverManWithPatch() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();

        // Update the deliverMan using partial update
        DeliverMan partialUpdatedDeliverMan = new DeliverMan();
        partialUpdatedDeliverMan.setId(deliverMan.getId());

        partialUpdatedDeliverMan
            .name(UPDATED_NAME)
            .age(UPDATED_AGE)
            .profilePicture(UPDATED_PROFILE_PICTURE)
            .profilePictureContentType(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);

        restDeliverManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliverMan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliverMan))
            )
            .andExpect(status().isOk());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
        DeliverMan testDeliverMan = deliverManList.get(deliverManList.size() - 1);
        assertThat(testDeliverMan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDeliverMan.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testDeliverMan.getProfilePicture()).isEqualTo(UPDATED_PROFILE_PICTURE);
        assertThat(testDeliverMan.getProfilePictureContentType()).isEqualTo(UPDATED_PROFILE_PICTURE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliverManDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliverMan() throws Exception {
        int databaseSizeBeforeUpdate = deliverManRepository.findAll().size();
        deliverMan.setId(count.incrementAndGet());

        // Create the DeliverMan
        DeliverManDTO deliverManDTO = deliverManMapper.toDto(deliverMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverManMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(deliverManDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliverMan in the database
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliverMan() throws Exception {
        // Initialize the database
        deliverManRepository.saveAndFlush(deliverMan);

        int databaseSizeBeforeDelete = deliverManRepository.findAll().size();

        // Delete the deliverMan
        restDeliverManMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliverMan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliverMan> deliverManList = deliverManRepository.findAll();
        assertThat(deliverManList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
