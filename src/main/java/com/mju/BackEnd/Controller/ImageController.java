package com.mju.BackEnd.Controller;

import com.mju.BackEnd.Dto.GenerateTemplate;
import com.mju.BackEnd.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") String id) {
        try {
            Path imagePath = Paths.get("images", id + ".jpg");
            //System.out.println(imagePath);
            File imageFile = imagePath.toFile();
            if (!imageFile.exists()) {
                return ResponseEntity.notFound().build();
            }
            Resource fileResource = new FileSystemResource(imageFile);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(fileResource);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
