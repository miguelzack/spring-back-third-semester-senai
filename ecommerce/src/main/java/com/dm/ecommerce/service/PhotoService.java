package com.dm.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class PhotoService {
    @Value("${upload.dir}")
    private String uploadDir;

    public String savePhoto(MultipartFile photo) throws IOException {
        if (photo == null || photo.isEmpty()) {
            throw new IllegalArgumentException("A imagem do produto é obrigatória.");
        }
        String originalName = photo.getOriginalFilename() == null ? "image" : photo.getOriginalFilename();
        String extension = originalName.contains(".") ? originalName.substring(originalName.lastIndexOf('.')) : "";
        String filename = UUID.randomUUID() + extension;
        Path directory = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(directory);
        Files.copy(photo.getInputStream(), directory.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/photos/" + filename;
    }
}
