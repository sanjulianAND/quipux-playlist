package com.example.playlist.service;

import com.example.playlist.domain.Playlist;
import com.example.playlist.domain.Song;
import com.example.playlist.dto.playlist.CreatePlaylistRequest;
import com.example.playlist.dto.playlist.PlaylistResponse;
import com.example.playlist.dto.playlist.SongDto;
import com.example.playlist.exception.BadRequestException;
import com.example.playlist.exception.NotFoundException;
import com.example.playlist.repository.PlaylistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public PlaylistResponse create(CreatePlaylistRequest request) {
        String name = normalize(request.getName());
        if (name.isBlank()) {
            throw new BadRequestException("name cannot be blank");
        }

        if (playlistRepository.existsByNameIgnoreCase(name)) {
            throw new BadRequestException("playlist already exists: " + name);
        }

        Playlist playlist = new Playlist(name, request.getDescription());

        List<SongDto> songs = request.getSongs() == null ? Collections.emptyList() : request.getSongs();
        for (SongDto s : songs) {
            Song song = new Song(
                    normalize(s.getTitle()),
                    normalizeNullable(s.getArtist()),
                    normalizeNullable(s.getAlbum()),
                    s.getYear(),
                    normalizeNullable(s.getGenre())
            );
            playlist.addSong(song);
        }

        Playlist saved = playlistRepository.save(playlist);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<PlaylistResponse> findAll() {
        return playlistRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlaylistResponse findByName(String listName) {
        String name = normalize(listName);
        Playlist playlist = playlistRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("playlist not found: " + name));
        return toResponse(playlist);
    }

    @Transactional
    public void deleteByName(String listName) {
        String name = normalize(listName);
        Playlist playlist = playlistRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("playlist not found: " + name));
        playlistRepository.delete(playlist);
    }

    private PlaylistResponse toResponse(Playlist playlist) {
        List<SongDto> songs = playlist.getSongs().stream()
                .map(s -> new SongDto(s.getTitle(), s.getArtist(), s.getAlbum(), s.getYear(), s.getGenre()))
                .toList();

        return new PlaylistResponse(
                playlist.getName(),
                playlist.getDescription(),
                songs
        );
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    private String normalizeNullable(String value) {
        String v = normalize(value);
        return v.isBlank() ? null : v;
    }
}
