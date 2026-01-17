package com.example.playlist.integration.spotify;

import com.example.playlist.exception.SpotifyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class SpotifyAuthClient {

    private final WebClient webClient;

    public SpotifyAuthClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://accounts.spotify.com")
                .build();
    }

    public SpotifyToken requestClientCredentialsToken(String clientId, String clientSecret) {
        String raw = clientId + ":" + clientSecret;
        String basic = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));

        return webClient.post()
                .uri("/api/token")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + basic)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .onStatus(s -> s.isError(), r -> r.bodyToMono(String.class)
                        .map(body -> new SpotifyException("Spotify token error: " + r.statusCode().value() + " - " + body)))
                .bodyToMono(SpotifyToken.class)
                .block();
    }
}
