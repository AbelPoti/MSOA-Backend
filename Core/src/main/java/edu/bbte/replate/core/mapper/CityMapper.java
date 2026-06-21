package edu.bbte.replate.core.mapper;

import edu.bbte.replate.core.dto.outgoing.CitySimpleOutDto;
import edu.bbte.replate.core.dto.outgoing.CityWithParentCountyOutDto;
import edu.bbte.replate.core.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountyMapper.class)
public interface CityMapper {
    CityWithParentCountyOutDto toWithParentOutDto(City city);

    CitySimpleOutDto toSimpleOutDto(City city);
}
