package edu.bbte.replate.core.dto.outgoing;

import java.util.List;

public record CategoryTreeOutDto(
        Long id,
        String name,
        CategoryParentOutDto parentCategory,
        List<CategoryTreeOutDto> subcategories
) {
}
