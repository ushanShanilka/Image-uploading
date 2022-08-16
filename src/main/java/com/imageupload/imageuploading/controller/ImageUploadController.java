package com.imageupload.imageuploading.controller;

import com.imageupload.imageuploading.entities.Image;
import com.imageupload.imageuploading.repo.ImageRepo;
import com.imageupload.imageuploading.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 8/16/2022
 **/
@RestController
public class ImageUploadController {

    @Autowired
    private ImageRepo repo;


    @PostMapping("upload-file")
    public String uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());

        repo.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return "Success !";
    }


    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = repo.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}
