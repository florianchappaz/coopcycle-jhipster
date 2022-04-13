package com.coopcycle.service.mapper;

import com.coopcycle.domain.Customer;
import com.coopcycle.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = { CityMapper.class, CooperativeMapper.class })
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Mapping(target = "city", source = "city", qualifiedByName = "id")
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "id")
    CustomerDTO toDto(Customer s);
}
