package com.hype.application.controller.image;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/files")
public class ImageController {

    private static final String IMAGE_DIR = "C:/uploads/";

    @GetMapping("/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename) {
        File file = new File(IMAGE_DIR + filename);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        try {
            byte[] image = Files.readAllBytes(file.toPath());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG) // ou IMAGE_PNG dependendo da extensão
                    .body(image);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error while reading image");
        }
    }
}