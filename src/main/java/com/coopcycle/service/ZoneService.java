package com.coopcycle.service;

import com.coopcycle.domain.Zone;
import com.coopcycle.repository.ZoneRepository;
import com.coopcycle.service.dto.ZoneDTO;
import com.coopcycle.service.mapper.ZoneMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Zone}.
 */
@Service
@Transactional
public class ZoneService {

    private final Logger log = LoggerFactory.getLogger(ZoneService.class);

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneService(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    /**
     * Save a zone.
     *
     * @param zoneDTO the entity to save.
     * @return the persisted entity.
     */
    public ZoneDTO save(ZoneDTO zoneDTO) {
        log.debug("Request to save Zone : {}", zoneDTO);
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDto(zone);
    }

    /**
     * Partially update a zone.
     *
     * @param zoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ZoneDTO> partialUpdate(ZoneDTO zoneDTO) {
        log.debug("Request to partially update Zone : {}", zoneDTO);

        return zoneRepository
            .findById(zoneDTO.getId())
            .map(existingZone -> {
                zoneMapper.partialUpdate(existingZone, zoneDTO);

                return existingZone;
            })
            .map(zoneRepository::save)
            .map(zoneMapper::toDto);
    }

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ZoneDTO> findAll() {
        log.debug("Request to get all Zones");
        return zoneRepository.findAll().stream().map(zoneMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the zones where Cooperative is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ZoneDTO> findAllWhereCooperativeIsNull() {
        log.debug("Request to get all zones where Cooperative is null");
        return StreamSupport
            .stream(zoneRepository.findAll().spliterator(), false)
            .filter(zone -> zone.getCooperative() == null)
            .map(zoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one zone by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ZoneDTO> findOne(Long id) {
        log.debug("Request to get Zone : {}", id);
        return zoneRepository.findById(id).map(zoneMapper::toDto);
    }

    /**
     * Delete the zone by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Zone : {}", id);
        zoneRepository.deleteById(id);
    }
}
