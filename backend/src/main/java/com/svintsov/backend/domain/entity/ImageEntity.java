package com.svintsov.backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IMAGES")
public class ImageEntity {

    @Id
    @Builder.Default
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "NAME", nullable = false)
    private String name;

    @Lob
    @Column(name = "IMAGE_DATA", nullable = false, columnDefinition = "TEXT")
    private String imageData;

}
