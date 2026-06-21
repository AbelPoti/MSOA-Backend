package edu.bbte.replate.controller;

import edu.bbte.replate.dto.outgoing.ListingExistenceOutDto;
import edu.bbte.replate.dto.outgoing.ListingOwnershipOutDto;
import edu.bbte.replate.service.ListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/listings")
@Slf4j
public class InternalListingController {
    @Autowired
    private ListingService listingService;

    @GetMapping("/{listingId}/ownership/{userId}")
    public ResponseEntity<ListingOwnershipOutDto> handleGetOwnership(
            @PathVariable long listingId,
            @PathVariable long userId
    ) {
        log.info("Handling GET /{}/ownership/{} request.", listingId, userId);

        boolean isOwner = listingService.isOwner(listingId, userId);

        var responseBody = new ListingOwnershipOutDto(isOwner);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{listingId}/existence")
    public ResponseEntity<ListingExistenceOutDto> handleGetExistence(
            @PathVariable long listingId
    ) {
        log.info("Handling GET /{}/existence request.", listingId);

        boolean doesExist = listingService.findById(listingId) != null;

        var responseBody = new ListingExistenceOutDto(doesExist);
        return ResponseEntity.ok(responseBody);
    }
}
