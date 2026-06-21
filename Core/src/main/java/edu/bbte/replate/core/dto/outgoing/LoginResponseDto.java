package edu.bbte.replate.core.dto.outgoing;

public record LoginResponseDto(
        String message,
        String token,
        Long userId
) {
}
