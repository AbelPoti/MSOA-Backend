package edu.bbte.replate.mapper;

import edu.bbte.replate.dto.incoming.ListingCreateDto;
import edu.bbte.replate.dto.incoming.ListingUpdateDto;
import edu.bbte.replate.dto.outgoing.ListingDetailedOutDto;
import edu.bbte.replate.dto.outgoing.ListingSimpleOutDto;
import edu.bbte.replate.model.Listing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CityMapper.class, CategoryMapper.class})
public interface ListingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "datePosted", ignore = true)
    Listing createDtoToListing(ListingCreateDto dto);

    @Mapping(target = "city", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "datePosted", ignore = true)
    Listing updateDtoToListing(ListingUpdateDto dto);

    ListingSimpleOutDto listingToSimpleOutDto(Listing listing);

    ListingDetailedOutDto listingToDetailedOutDto(Listing listing);
}
