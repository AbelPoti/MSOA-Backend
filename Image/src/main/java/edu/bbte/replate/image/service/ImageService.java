package edu.bbte.replate.image.service;

import edu.bbte.replate.image.dto.outgoing.ImageDownloadDto;
import edu.bbte.replate.image.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image findById(Long id);

    Image findByListingIdAndImageId(Long listingId, Long imageId);

    Image findByName(String imageName);

    List<Image> findImagesByListingId(Long listingId);

    Image upload(Long listingId, MultipartFile file);

    ImageDownloadDto download(Long listingId, Long imageId);

    void delete(Long listingId, Long imageId);
}
