package com.dm.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class PhotoService {
    private final RestClient restClient;

    @Value("${supabase.url:}")
    private String supabaseUrl;

    @Value("${supabase.key:}")
    private String supabaseKey;

    @Value("${supabase.bucket:products}")
    private String bucket;

    public PhotoService() {
        this.restClient = RestClient.create();
    }

    public String savePhoto(MultipartFile photo) throws IOException {
        if (photo == null || photo.isEmpty()) {
            throw new IllegalArgumentException("A imagem do produto é obrigatória.");
        }
        if (supabaseUrl == null || supabaseUrl.isBlank() || supabaseKey == null || supabaseKey.isBlank()) {
            throw new IllegalStateException("Supabase Storage não está configurado.");
        }

        String originalName = photo.getOriginalFilename() == null ? "image" : photo.getOriginalFilename();
        String extension = originalName.contains(".") ? originalName.substring(originalName.lastIndexOf('.')) : "";
        String path = UUID.randomUUID() + extension;
        restClient.post()
                .uri(supabaseUrl + "/storage/v1/object/" + bucket + "/" + path)
                .header("apikey", supabaseKey)
                .header("Authorization", "Bearer " + supabaseKey)
                .contentType(MediaType.parseMediaType(photo.getContentType() == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : photo.getContentType()))
                .body(photo.getBytes())
                .retrieve()
                .toBodilessEntity();

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + path;
    }
}
