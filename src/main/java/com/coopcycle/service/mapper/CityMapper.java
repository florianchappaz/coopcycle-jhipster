package com.coopcycle.service.mapper;

import com.coopcycle.domain.City;
import com.coopcycle.service.dto.CityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring", uses = { ZoneMapper.class })
public interface CityMapper extends EntityMapper<CityDTO, City> {
    @Mapping(target = "zone", source = "zone", qualifiedByName = "id")
    CityDTO toDto(City s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CityDTO toDtoId(City city);
}
