package com.coopcycle.service.mapper;

import com.coopcycle.domain.Meal;
import com.coopcycle.service.dto.MealDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Meal} and its DTO {@link MealDTO}.
 */
@Mapper(componentModel = "spring", uses = { RestaurantMapper.class })
public interface MealMapper extends EntityMapper<MealDTO, Meal> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "id")
    MealDTO toDto(Meal s);
}
