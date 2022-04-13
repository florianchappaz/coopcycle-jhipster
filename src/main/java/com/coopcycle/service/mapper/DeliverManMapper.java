package com.coopcycle.service.mapper;

import com.coopcycle.domain.DeliverMan;
import com.coopcycle.service.dto.DeliverManDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliverMan} and its DTO {@link DeliverManDTO}.
 */
@Mapper(componentModel = "spring", uses = { CooperativeMapper.class })
public interface DeliverManMapper extends EntityMapper<DeliverManDTO, DeliverMan> {
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "id")
    DeliverManDTO toDto(DeliverMan s);
}
