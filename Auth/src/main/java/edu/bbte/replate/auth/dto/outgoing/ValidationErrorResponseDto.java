package edu.bbte.replate.auth.dto.outgoing;

import java.util.Map;

public record ValidationErrorResponseDto(
        Map<String, String> errors
) {
}
