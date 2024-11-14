package com.svintsov.backend.rest.controller;

import static java.lang.String.format;

import com.svintsov.backend.domain.entity.ImageEntity;
import com.svintsov.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {
        byte[] imageAsByteArray = imageService.getImageAsByteArray(name);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION,  format("inline; filename=\"%s\"", name))
                .body(imageAsByteArray);
    }

    @PutMapping("/{name}")
    public String uploadImage(@RequestBody byte[] imageData, @PathVariable("name") String name) {
        ImageEntity imageEntity = imageService.uploadImage( name, imageData);
        return imageEntity.getName();
    }
}
