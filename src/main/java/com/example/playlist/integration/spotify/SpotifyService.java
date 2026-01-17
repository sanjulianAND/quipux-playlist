package com.example.playlist.integration.spotify;

import com.example.playlist.exception.SpotifyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class SpotifyService {

    private final SpotifyAuthClient authClient;
    private final SpotifyClient spotifyClient;

    private final String clientId;
    private final String clientSecret;

    private String cachedToken;
    private Instant cachedTokenExpiresAt;

    public SpotifyService(
            SpotifyAuthClient authClient,
            SpotifyClient spotifyClient,
            @Value("${spotify.client-id:}") String clientId,
            @Value("${spotify.client-secret:}") String clientSecret
    ) {
        this.authClient = authClient;
        this.spotifyClient = spotifyClient;
        this.clientId = clientId == null ? "" : clientId.trim();
        this.clientSecret = clientSecret == null ? "" : clientSecret.trim();
    }

    public List<String> getGenres() {
        if (clientId.isBlank() || clientSecret.isBlank()) {
            throw new SpotifyException("Spotify credentials are not configured");
        }

        String token = getValidToken();
        SpotifyClient.GenresResponse resp = spotifyClient.getAvailableGenres(token);
        if (resp == null || resp.getGenres() == null) return Collections.emptyList();
        return resp.getGenres();
    }

    private String getValidToken() {
        Instant now = Instant.now();
        if (cachedToken != null && cachedTokenExpiresAt != null && now.isBefore(cachedTokenExpiresAt)) {
            return cachedToken;
        }

        SpotifyToken token = authClient.getClientCredentialsToken(clientId, clientSecret);
        if (token == null || token.getAccessToken() == null || token.getAccessToken().isBlank()) {
            throw new SpotifyException("Unable to obtain spotify token");
        }

        long expiresIn = token.getExpiresIn() == null ? 0L : token.getExpiresIn();
        cachedToken = token.getAccessToken();
        cachedTokenExpiresAt = now.plusSeconds(Math.max(1, expiresIn - 30));

        return cachedToken;
    }
}
