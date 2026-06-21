package edu.bbte.replate.core.dto.outgoing;

public record CategorySimpleOutDto(
        Long id,
        String name,
        Long parentCategoryId
) {
}
