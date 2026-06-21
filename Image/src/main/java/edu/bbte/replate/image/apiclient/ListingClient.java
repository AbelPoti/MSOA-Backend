package edu.bbte.replate.image.apiclient;

import edu.bbte.replate.image.dto.incoming.ListingExistenceInDto;
import edu.bbte.replate.image.dto.incoming.ListingOwnershipInDto;
import edu.bbte.replate.image.utils.FeignJwtConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="listing-service",
        url="${core.service.uri}",
        configuration = FeignJwtConfiguration.class
)
public interface ListingClient {
    @GetMapping("internal/listings/{listingId}/ownership/{userId}")
    ListingOwnershipInDto isOwner(@PathVariable long listingId, @PathVariable long userId);

    @GetMapping("internal/listings/{listingId}/existence")
    ListingExistenceInDto doesExist(@PathVariable long listingId);
}
