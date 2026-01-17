package com.example.playlist.controller;

import com.example.playlist.integration.spotify.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenresController {

    private final SpotifyService spotifyService;

    public GenresController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/genres")
    public ResponseEntity<List<String>> genres() {
        return ResponseEntity.ok(spotifyService.getGenres());
    }
}
