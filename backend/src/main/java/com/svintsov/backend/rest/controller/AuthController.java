package com.svintsov.backend.rest.controller;

import static java.lang.String.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svintsov.backend.dto.KeycloakTokenResponseDto;
import com.svintsov.backend.exception.AuthorizationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${application.keycloak.authorization.code.callback}")
    private String authorizationCodeCallback;

    @Value("${application.keycloak.client.secret}")
    private String keycloakClientSecret;

    @Value("${application.keycloak.base.url}")
    private String keycloakBaseUrl;

    @Value("${application.authorization.frontend.redirect.url}")
    private String frontendRedirectUrl;

    @GetMapping("/codeCallback")
    public ResponseEntity<Void> authCodeCallback(@RequestParam("code") String authorizationCode, HttpServletResponse response) {

        log.info("Authorization code: {}", authorizationCode);

        Map<String, String> formData = Map.of(
                "grant_type", "authorization_code",
                "code", authorizationCode,
                "client_id", "authz-servlet",
                "client_secret", keycloakClientSecret,
                "redirect_uri",authorizationCodeCallback);

        String formBody = formData.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                        + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(keycloakBaseUrl + "realms/quickstart/protocol/openid-connect/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();


        KeycloakTokenResponseDto tokenResponse;
        try (HttpClient client = HttpClient.newHttpClient();) {
            HttpResponse<String> keycloakResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            tokenResponse = objectMapper.readValue(keycloakResponse.body(), KeycloakTokenResponseDto.class);
        } catch (Exception e) {
            log.error("Error getting access token for code {}", authorizationCode, e);
            throw new AuthorizationException(format("Error getting access token for code %s", authorizationCode));
        }

        Cookie accessTokenCookie = new Cookie("authz_access_token", tokenResponse.getAccessToken());
        accessTokenCookie.setHttpOnly(false);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge((int) TimeUnit.HOURS.toSeconds(15));
        response.addCookie(accessTokenCookie);

        return ResponseEntity.status(HttpStatus.FOUND.value())
                .header(HttpHeaders.LOCATION, frontendRedirectUrl)
                .build();
    }

}
