package edu.bbte.replate.core.dto.outgoing;

import java.util.Map;

public record ValidationErrorResponseDto(
        Map<String, String> errors
) {
}
