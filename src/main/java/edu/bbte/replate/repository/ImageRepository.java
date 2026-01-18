package edu.bbte.replate.repository;

import edu.bbte.replate.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImageName(String imageName);
}
