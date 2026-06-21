package edu.bbte.replate.image.mapper;

import edu.bbte.replate.image.dto.outgoing.ImageOutDto;
import edu.bbte.replate.image.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageOutDto imageToOutDto(Image image);
}
