package edu.bbte.replate.image.repository;

import edu.bbte.replate.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {


    Image findImageByImageName(String imageName);
}
