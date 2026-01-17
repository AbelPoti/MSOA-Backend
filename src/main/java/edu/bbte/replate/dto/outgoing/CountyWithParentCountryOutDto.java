package edu.bbte.replate.dto.outgoing;

public record CountyWithParentCountryOutDto(
        Long id,
        String name,
        CountrySimpleOutDto country
) {
}
