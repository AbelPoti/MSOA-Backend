package edu.bbte.replate.image.dto.outgoing;

import java.util.Map;

public record ValidationErrorResponseDto(
        Map<String, String> errors
) {
}
