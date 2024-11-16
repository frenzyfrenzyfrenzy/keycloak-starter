package com.svintsov.backend.service;

import static java.lang.String.format;

import com.svintsov.backend.domain.entity.ImageEntity;
import com.svintsov.backend.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<String> getAllImageNames() {
        return imageRepository.findAll().stream()
                .map(ImageEntity::getName)
                .toList();
    }

    public ImageEntity uploadImage(String name, byte[] content) {
        String encoded = Base64.getEncoder().encodeToString(content);
        return imageRepository.save(ImageEntity.builder()
                .imageData(encoded)
                .name(name)
                .build());
    }

    public byte[] getImageAsByteArray(String name) {
        ImageEntity imageEntity = imageRepository.findFirstByName(name)
                .orElseThrow(() -> new NoSuchElementException(format("Image with name %s not found", name)));
        return Base64.getDecoder().decode(imageEntity.getImageData());
    }

}
