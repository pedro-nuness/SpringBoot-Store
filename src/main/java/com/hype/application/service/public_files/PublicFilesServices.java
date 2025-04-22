package com.hype.application.service.public_files;


import com.hype.application.config.FileStorageProperties;
import com.hype.application.exception.EventErrorTransferingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class PublicFilesServices {

    @Autowired
    private FileStorageProperties fileStorageProperties;


    public String UploadFile(MultipartFile file)
    {
        String filename = UUID.randomUUID().toString();
        String uploadPath = fileStorageProperties.getUploadDir() + filename;
        String imageUrl = fileStorageProperties.getBaseUrl() + filename;

        try {
            file.transferTo(new File(uploadPath));
        } catch (Exception e) {
            throw new EventErrorTransferingFile("An error occurred while uploading file: ", e.getMessage());
        }

        return imageUrl;
    }

}
