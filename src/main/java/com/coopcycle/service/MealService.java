package com.coopcycle.service;

import com.coopcycle.domain.Meal;
import com.coopcycle.repository.MealRepository;
import com.coopcycle.service.dto.MealDTO;
import com.coopcycle.service.mapper.MealMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Meal}.
 */
@Service
@Transactional
public class MealService {

    private final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository mealRepository;

    private final MealMapper mealMapper;

    public MealService(MealRepository mealRepository, MealMapper mealMapper) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    /**
     * Save a meal.
     *
     * @param mealDTO the entity to save.
     * @return the persisted entity.
     */
    public MealDTO save(MealDTO mealDTO) {
        log.debug("Request to save Meal : {}", mealDTO);
        Meal meal = mealMapper.toEntity(mealDTO);
        meal = mealRepository.save(meal);
        return mealMapper.toDto(meal);
    }

    /**
     * Partially update a meal.
     *
     * @param mealDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MealDTO> partialUpdate(MealDTO mealDTO) {
        log.debug("Request to partially update Meal : {}", mealDTO);

        return mealRepository
            .findById(mealDTO.getId())
            .map(existingMeal -> {
                mealMapper.partialUpdate(existingMeal, mealDTO);

                return existingMeal;
            })
            .map(mealRepository::save)
            .map(mealMapper::toDto);
    }

    /**
     * Get all the meals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MealDTO> findAll() {
        log.debug("Request to get all Meals");
        return mealRepository.findAll().stream().map(mealMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one meal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MealDTO> findOne(Long id) {
        log.debug("Request to get Meal : {}", id);
        return mealRepository.findById(id).map(mealMapper::toDto);
    }

    /**
     * Delete the meal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Meal : {}", id);
        mealRepository.deleteById(id);
    }
}
