package com.coopcycle.service.mapper;

import com.coopcycle.domain.Restaurant;
import com.coopcycle.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring", uses = { CityMapper.class, CooperativeMapper.class })
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {
    @Mapping(target = "city", source = "city", qualifiedByName = "id")
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "id")
    RestaurantDTO toDto(Restaurant s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RestaurantDTO toDtoId(Restaurant restaurant);
}
