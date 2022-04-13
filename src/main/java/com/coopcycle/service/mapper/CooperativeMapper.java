package com.coopcycle.service.mapper;

import com.coopcycle.domain.Cooperative;
import com.coopcycle.service.dto.CooperativeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperative} and its DTO {@link CooperativeDTO}.
 */
@Mapper(componentModel = "spring", uses = { ZoneMapper.class })
public interface CooperativeMapper extends EntityMapper<CooperativeDTO, Cooperative> {
    @Mapping(target = "zone", source = "zone", qualifiedByName = "id")
    CooperativeDTO toDto(Cooperative s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoId(Cooperative cooperative);
}
