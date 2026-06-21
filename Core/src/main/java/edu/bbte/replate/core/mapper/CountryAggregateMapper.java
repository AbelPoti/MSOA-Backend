package edu.bbte.replate.core.mapper;

import edu.bbte.replate.core.dto.outgoing.CountryWithChildCountiesOutDto;
import edu.bbte.replate.core.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountyMapper.class)
public interface CountryAggregateMapper {
    CountryWithChildCountiesOutDto toWithChildrenDto(Country country);
}
