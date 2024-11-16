package com.svintsov.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class KeycloakTokenResponseDto {

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("expires_in")
    int expiresIn;

    @JsonProperty("refresh_expires_in")
    int refreshExpiresIn;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("id_token")
    String idToken;

    @JsonProperty("not-before-policy")
    int notBeforePolicy;

    @JsonProperty("session_state")
    String sessionState;

    @JsonProperty("scope")
    String scope;

}