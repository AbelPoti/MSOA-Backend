package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.CountryWithChildCountiesOutDto;
import edu.bbte.replate.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountyMapper.class)
public interface CountryAggregateMapper {
    CountryWithChildCountiesOutDto toWithChildrenDto(Country country);
}
