package com.example.playlist.integration.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SpotifyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public GenresResponse getAvailableGenres(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<GenresResponse> resp = restTemplate.exchange(
                "https://api.spotify.com/v1/recommendations/available-genre-seeds",
                HttpMethod.GET,
                entity,
                GenresResponse.class
        );

        return resp.getBody();
    }

    public static class GenresResponse {
        @JsonProperty("genres")
        private List<String> genres;

        public GenresResponse() {}

        public List<String> getGenres() { return genres; }

        public void setGenres(List<String> genres) { this.genres = genres; }
    }
}
