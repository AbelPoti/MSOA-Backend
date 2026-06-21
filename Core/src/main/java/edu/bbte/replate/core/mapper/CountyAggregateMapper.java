package edu.bbte.replate.core.mapper;

import edu.bbte.replate.core.dto.outgoing.CountyWithChildCitiesOutDto;
import edu.bbte.replate.core.model.County;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface CountyAggregateMapper {
    CountyWithChildCitiesOutDto toWithChildrenDto(County county);
}
