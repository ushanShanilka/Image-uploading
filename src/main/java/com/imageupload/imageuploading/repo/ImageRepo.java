package com.imageupload.imageuploading.repo;

import com.imageupload.imageuploading.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 8/16/2022
 **/
@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);
}
