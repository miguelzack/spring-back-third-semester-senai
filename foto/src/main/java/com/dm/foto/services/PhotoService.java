package com.dm.foto.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class PhotoService {

    @Value("${upload.dir}")
    private String uploadDir;

    public String savePhoto(MultipartFile photo) throws IOException {

        String originalName = photo.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String newName = UUID.randomUUID() + extension;
        Path directory = Path.of(uploadDir);
        Files.createDirectories(directory);

        Path pathUrl = directory.resolve(newName);

        Files.copy(photo.getInputStream(), pathUrl);

        return uploadDir + "/" + newName;


    }
}
