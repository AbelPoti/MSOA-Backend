package edu.bbte.replate.core.dto.outgoing;

public record CountyWithParentCountryOutDto(
        Long id,
        String name,
        CountrySimpleOutDto country
) {
}
