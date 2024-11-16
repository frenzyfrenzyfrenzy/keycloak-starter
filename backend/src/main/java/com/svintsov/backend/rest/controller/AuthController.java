package com.svintsov.backend.rest.controller;

import static java.lang.String.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svintsov.backend.dto.KeycloakTokenResponseDto;
import com.svintsov.backend.exception.AuthorizationException;
import com.svintsov.backend.rest.dto.TokenResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/codeCallback")
    public TokenResponseDto authCodeCallback(@RequestParam("code") String authorizationCode) {

        log.info("Authorization code: {}", authorizationCode);

        Map<String, String> formData = Map.of(
                "grant_type", "authorization_code",
                "code", authorizationCode,
                "client_id", "authz-servlet",
                "client_secret", "secret",
                "redirect_uri", "http://localhost:8080/api/auth/codeCallback"
        );

        String formBody = formData.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/realms/quickstart/protocol/openid-connect/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();


        KeycloakTokenResponseDto tokenResponse;
        try (HttpClient client = HttpClient.newHttpClient();) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            tokenResponse = objectMapper.readValue(response.body(), KeycloakTokenResponseDto.class);
        } catch (Exception e) {
            log.error("Error getting access token for code {}", authorizationCode, e);
            throw new AuthorizationException(format("Error getting access token for code %s", authorizationCode));
        }

        return TokenResponseDto.builder()
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .idToken(tokenResponse.getIdToken())
                .scope(tokenResponse.getScope())
                .build();
    }

}
