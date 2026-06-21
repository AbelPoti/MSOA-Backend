package edu.bbte.replate.core.mapper;

import edu.bbte.replate.core.dto.outgoing.CountySimpleOutDto;
import edu.bbte.replate.core.dto.outgoing.CountyWithParentCountryOutDto;
import edu.bbte.replate.core.model.County;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface CountyMapper {
    CountyWithParentCountryOutDto toWithParentOutDto(County county);

    CountySimpleOutDto toSimpleOutDto(County county);
}
