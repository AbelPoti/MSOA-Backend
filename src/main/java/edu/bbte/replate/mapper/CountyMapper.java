package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.CountySimpleOutDto;
import edu.bbte.replate.dto.outgoing.CountyWithParentCountryOutDto;
import edu.bbte.replate.model.County;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface CountyMapper {
    CountyWithParentCountryOutDto toWithParentOutDto(County county);

    CountySimpleOutDto toSimpleOutDto(County county);
}
