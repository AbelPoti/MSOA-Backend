package edu.bbte.replate.dto.outgoing;

import java.util.Map;

public record ValidationErrorResponseDto(
        Map<String, String> errors
) {
}
