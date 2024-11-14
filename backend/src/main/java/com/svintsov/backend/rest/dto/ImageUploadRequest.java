package com.svintsov.backend.rest.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class ImageUploadRequest {

    String name;
    String content;

}
