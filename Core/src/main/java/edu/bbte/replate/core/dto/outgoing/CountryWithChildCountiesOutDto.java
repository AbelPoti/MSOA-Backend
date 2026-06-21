package edu.bbte.replate.core.dto.outgoing;

import java.util.List;

public record CountryWithChildCountiesOutDto(
        Long id,
        String name,
        List<CountySimpleOutDto> counties
) {
}
