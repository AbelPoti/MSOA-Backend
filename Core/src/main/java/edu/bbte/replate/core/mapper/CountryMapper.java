package edu.bbte.replate.core.mapper;

import edu.bbte.replate.core.dto.outgoing.CountrySimpleOutDto;
import edu.bbte.replate.core.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountrySimpleOutDto toSimpleOutDto(Country country);
}
