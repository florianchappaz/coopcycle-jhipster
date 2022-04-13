package com.coopcycle.service;

import com.coopcycle.domain.DeliverMan;
import com.coopcycle.repository.DeliverManRepository;
import com.coopcycle.service.dto.DeliverManDTO;
import com.coopcycle.service.mapper.DeliverManMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliverMan}.
 */
@Service
@Transactional
public class DeliverManService {

    private final Logger log = LoggerFactory.getLogger(DeliverManService.class);

    private final DeliverManRepository deliverManRepository;

    private final DeliverManMapper deliverManMapper;

    public DeliverManService(DeliverManRepository deliverManRepository, DeliverManMapper deliverManMapper) {
        this.deliverManRepository = deliverManRepository;
        this.deliverManMapper = deliverManMapper;
    }

    /**
     * Save a deliverMan.
     *
     * @param deliverManDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliverManDTO save(DeliverManDTO deliverManDTO) {
        log.debug("Request to save DeliverMan : {}", deliverManDTO);
        DeliverMan deliverMan = deliverManMapper.toEntity(deliverManDTO);
        deliverMan = deliverManRepository.save(deliverMan);
        return deliverManMapper.toDto(deliverMan);
    }

    /**
     * Partially update a deliverMan.
     *
     * @param deliverManDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DeliverManDTO> partialUpdate(DeliverManDTO deliverManDTO) {
        log.debug("Request to partially update DeliverMan : {}", deliverManDTO);

        return deliverManRepository
            .findById(deliverManDTO.getId())
            .map(existingDeliverMan -> {
                deliverManMapper.partialUpdate(existingDeliverMan, deliverManDTO);

                return existingDeliverMan;
            })
            .map(deliverManRepository::save)
            .map(deliverManMapper::toDto);
    }

    /**
     * Get all the deliverMen.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DeliverManDTO> findAll() {
        log.debug("Request to get all DeliverMen");
        return deliverManRepository.findAll().stream().map(deliverManMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one deliverMan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeliverManDTO> findOne(Long id) {
        log.debug("Request to get DeliverMan : {}", id);
        return deliverManRepository.findById(id).map(deliverManMapper::toDto);
    }

    /**
     * Delete the deliverMan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DeliverMan : {}", id);
        deliverManRepository.deleteById(id);
    }
}
