package edu.bbte.replate.dto.outgoing;

import java.util.List;

public record CategoryTreeOutDto(
        Long id,
        String name,
        CategoryParentOutDto parentCategory,
        List<CategoryTreeOutDto> subcategories
) {
}
