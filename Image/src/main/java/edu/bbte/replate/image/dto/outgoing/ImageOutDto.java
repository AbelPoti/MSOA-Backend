package edu.bbte.replate.image.dto.outgoing;

public record ImageOutDto(
        Long id,
        String imageName,
        String imageMimeType
) {
}
