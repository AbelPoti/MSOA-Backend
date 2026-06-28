package edu.bbte.replate.image.controller;

import edu.bbte.replate.image.apiclient.ListingClient;
import edu.bbte.replate.image.dto.outgoing.ImageDownloadDto;
import edu.bbte.replate.image.dto.outgoing.ImageOutDto;
import edu.bbte.replate.image.dto.outgoing.SimpleMessageResponseDto;
import edu.bbte.replate.image.mapper.ImageMapper;
import edu.bbte.replate.image.model.Image;
import edu.bbte.replate.image.service.ImageService;
import edu.bbte.replate.image.utils.JwtPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/images/listings/{listingId}/images")
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ListingClient listingClient;

    @Autowired
    private ImageMapper imageMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SimpleMessageResponseDto> handleUploadImage(
            @PathVariable long listingId,
            @RequestPart("file")MultipartFile file,
            @AuthenticationPrincipal JwtPrincipal principal
    ) {
        log.info("Handling POST /listing/{}/images request.", listingId);

        // Call other service via ListingClient to see if the listing exists
        if (!listingClient.doesExist(listingId).doesExist()) {
            var responseBody = new SimpleMessageResponseDto(
                    "Targeted listing does not exist, cannot post image to it!"
            );
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        // Call other service via ListingClient to see if the listing is owned by the requesting user
        if (!listingClient.isOwner(listingId, principal.userId()).isOwner()) {
            var responseBody = new SimpleMessageResponseDto(
                    "Requesting user does not own targeted listing, cannot post image to it!"
            );
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }

        Image createdImage = imageService.upload(listingId, file);

        URI createdUri = URI.create("/listings/" + listingId + "/images/" + createdImage.getId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(createdUri);

        var responseBody = new SimpleMessageResponseDto("New image uploaded successfully.");

        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{imageId}/download")
    public ResponseEntity<Resource> handleDownloadImage(
            @PathVariable long listingId,
            @PathVariable long imageId
    ) {
        ImageDownloadDto downloadDto = imageService.download(listingId, imageId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(downloadDto.mimeType()))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + downloadDto.fileName() + "\""
                )
                .body(downloadDto.resource());
    }

    @GetMapping
    public ResponseEntity<List<ImageOutDto>> handleGetAllImagesByListingId(@PathVariable long listingId) {
        log.info("Handling GET /listings/{}/images request.", listingId);

        List<ImageOutDto> outDtos =  imageService.findImagesByListingId(listingId)
                .stream()
                .map(imageMapper::imageToOutDto)
                .toList();

        return ResponseEntity.ok(outDtos);
    }

    @GetMapping("/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ImageOutDto> handleGetImageOfListingById(
            @PathVariable long listingId,
            @PathVariable long imageId
    ) {
        log.info("Handling GET /listings/{}/images/{} request.", listingId, imageId);

        // Exceptions handled in service
        Image image = imageService.findByListingIdAndImageId(listingId, imageId);

        return ResponseEntity.ok(imageMapper.imageToOutDto(image));
    }

    @DeleteMapping("/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<SimpleMessageResponseDto> handleDeleteImageOfListingById(
            @PathVariable long listingId,
            @PathVariable long imageId,
            @AuthenticationPrincipal JwtPrincipal principal
    ) {
        log.info("Handling DELETE /listings/{}/images/{} request.", listingId, imageId);

        // Check if the listing exists and is owned by the requesting user
        if (!listingClient.doesExist(listingId).doesExist()) {
            var responseBody = new SimpleMessageResponseDto(
                    "Targeted listing does not exist, cannot delete image from it!"
            );

            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        if (!listingClient.isOwner(listingId, principal.userId()).isOwner()) {
            var responseBody = new SimpleMessageResponseDto(
                    "Requesting user does not own targeted listing, cannot delete image from it!"
            );
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }

        // Exceptions handled in service
        imageService.delete(listingId, imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
