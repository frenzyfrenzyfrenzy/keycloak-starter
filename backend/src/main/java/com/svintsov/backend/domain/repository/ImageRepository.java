package com.svintsov.backend.domain.repository;

import com.svintsov.backend.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

    Optional<ImageEntity> findFirstByName(String name);

}
