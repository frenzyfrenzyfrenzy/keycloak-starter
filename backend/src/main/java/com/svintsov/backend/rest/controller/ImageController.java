package com.svintsov.backend.rest.controller;

import com.svintsov.backend.domain.entity.ImageEntity;
import com.svintsov.backend.rest.dto.ImageUploadRequest;
import com.svintsov.backend.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private ImageService imageService;

    @GetMapping
    public String getImage(@RequestParam("name") String name) {
        return imageService.getImage(name).getImageData();
    }

    @PutMapping
    public String addUser(@RequestBody ImageUploadRequest request) {
        ImageEntity imageEntity = imageService.uploadImage(request.getName(), request.getContent());
        return imageEntity.getImageData();
    }
}
