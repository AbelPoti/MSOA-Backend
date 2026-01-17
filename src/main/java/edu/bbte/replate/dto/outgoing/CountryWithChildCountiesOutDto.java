package edu.bbte.replate.dto.outgoing;

import java.util.List;

public record CountryWithChildCountiesOutDto(
        Long id,
        String name,
        List<CountySimpleOutDto> counties
) {
}
