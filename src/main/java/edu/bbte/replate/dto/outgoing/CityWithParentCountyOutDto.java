package edu.bbte.replate.dto.outgoing;

public record CityWithParentCountyOutDto(
        Long id,
        String name,
        CountyWithParentCountryOutDto county
) {
}
