package com.mju.BackEnd.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import com.mju.BackEnd.Dto.*;
import reactor.core.publisher.Mono;

@Service
public class ImageService {
    @Value("${serverAddr}")
    private String serverAddress;
    private static final String IMAGE_DIR = "images";

    public ImageService() {
        // Ensure the directory exists
        File directory = new File(IMAGE_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public Mono<GenerateTemplate> downloadImage(GenerateTemplate source) {
        return Mono.fromCallable(() -> {
            URL url = new URL(source.getUrl());
            String destUrl = source.getID() + ".jpg";
            Path destinationPath = Paths.get(IMAGE_DIR, destUrl);
            try (InputStream in = url.openStream()) {
                Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                //System.out.println("Image Saved: " + destUrl);
                source.setUrl(destUrl);
            }
            source.setUrl(serverAddress + "/images/" + source.getID());
            return source;
        }).onErrorResume(e -> Mono.error(new RuntimeException("Failed to download image", e)));
    }
}
