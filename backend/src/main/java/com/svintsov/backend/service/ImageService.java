package com.svintsov.backend.service;

import static java.lang.String.format;

import com.svintsov.backend.domain.entity.ImageEntity;
import com.svintsov.backend.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageEntity uploadImage(String name, String content) {
        return imageRepository.save(ImageEntity.builder()
                .imageData(content)
                .name(name)
                .build());
    }

    public ImageEntity getImage(String name) {
        return imageRepository.findFirstByName(name)
                .orElseThrow(() -> new NoSuchElementException(format("Image with name %s not found", name)));
    }

}
