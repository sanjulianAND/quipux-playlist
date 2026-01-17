package com.example.playlist.integration.spotify;

import com.example.playlist.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SpotifyService {

    private final SpotifyAuthClient authClient;
    private final SpotifyClient spotifyClient;

    private final String clientId;
    private final String clientSecret;

    private volatile String cachedToken;
    private volatile Instant expiresAt = Instant.EPOCH;

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
        String token = getAccessToken();
        return spotifyClient.getAvailableGenres(token);
    }

    private String getAccessToken() {
        if (clientId.isBlank() || clientSecret.isBlank()) {
            throw new BadRequestException("Spotify credentials are not configured");
        }

        Instant now = Instant.now();
        if (cachedToken != null && now.isBefore(expiresAt.minusSeconds(30))) {
            return cachedToken;
        }

        SpotifyToken token = authClient.requestClientCredentialsToken(clientId, clientSecret);
        if (token == null || token.getAccessToken() == null || token.getAccessToken().isBlank()) {
            throw new BadRequestException("Unable to obtain Spotify token");
        }

        cachedToken = token.getAccessToken();
        expiresAt = now.plusSeconds(Math.max(0, token.getExpiresIn()));
        return cachedToken;
    }
}
