package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.CountyWithChildCitiesOutDto;
import edu.bbte.replate.model.County;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface CountyAggregateMapper {
    CountyWithChildCitiesOutDto toWithChildrenDto(County county);
}
