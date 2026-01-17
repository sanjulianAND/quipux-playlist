package com.example.playlist.integration.spotify;

import com.example.playlist.exception.SpotifyException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SpotifyClient {

    private final WebClient webClient;

    public SpotifyClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.spotify.com")
                .build();
    }

    public List<String> getAvailableGenres(String accessToken) {
        SpotifyGenresResponse res = webClient.get()
                .uri("/v1/recommendations/available-genre-seeds")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(s -> s.isError(), r -> r.bodyToMono(String.class)
                        .map(body -> new SpotifyException("Spotify genres error: " + r.statusCode().value() + " - " + body)))
                .bodyToMono(SpotifyGenresResponse.class)
                .block();

        return res == null || res.getGenres() == null ? List.of() : res.getGenres();
    }

    public static class SpotifyGenresResponse {
        private List<String> genres;

        public SpotifyGenresResponse() {}

        public List<String> getGenres() { return genres; }

        public void setGenres(List<String> genres) { this.genres = genres; }
    }
}
