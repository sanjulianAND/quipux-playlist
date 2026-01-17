package com.example.playlist.controller;

import com.example.playlist.dto.playlist.CreatePlaylistRequest;
import com.example.playlist.dto.playlist.PlaylistResponse;
import com.example.playlist.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistResponse> create(@Valid @RequestBody CreatePlaylistRequest request) {
        PlaylistResponse created = playlistService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(created.getName())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> findAll() {
        return ResponseEntity.ok(playlistService.findAll());
    }

    @GetMapping("/{listName}")
    public ResponseEntity<PlaylistResponse> findByName(@PathVariable String listName) {
        return ResponseEntity.ok(playlistService.findByName(listName));
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> delete(@PathVariable String listName) {
        playlistService.deleteByName(listName);
        return ResponseEntity.noContent().build();
    }
}
