package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.CitySimpleOutDto;
import edu.bbte.replate.dto.outgoing.CityWithParentCountyOutDto;
import edu.bbte.replate.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CountyMapper.class)
public interface CityMapper {
    CityWithParentCountyOutDto toWithParentOutDto(City city);

    CitySimpleOutDto toSimpleOutDto(City city);
}
