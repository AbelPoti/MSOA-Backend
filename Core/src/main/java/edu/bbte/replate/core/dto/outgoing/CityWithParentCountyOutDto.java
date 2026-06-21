package edu.bbte.replate.core.dto.outgoing;

public record CityWithParentCountyOutDto(
        Long id,
        String name,
        CountyWithParentCountryOutDto county
) {
}
