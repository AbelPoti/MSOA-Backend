package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.outgoing.ImageOutDto;
import edu.bbte.replate.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageOutDto imageToOutDto(Image image);
}
