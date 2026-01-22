package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.CountrySimpleOutDto;
import edu.bbte.replate.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountrySimpleOutDto toSimpleOutDto(Country country);
}
