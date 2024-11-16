package com.svintsov.backend.rest.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenResponseDto {

    String accessToken;
    String refreshToken;
    String idToken;
    String scope;

}
