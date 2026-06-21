package edu.bbte.replate.core.dto.outgoing;

import java.util.List;

public record CountyWithChildCitiesOutDto(
        Long id,
        String name,
        List<CitySimpleOutDto> cities
) {
}
