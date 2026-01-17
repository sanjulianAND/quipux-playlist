package com.example.playlist.dto.playlist;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class PlaylistResponse {

    @JsonAlias({"nombre", "name"})
    private String name;

    @JsonAlias({"descripcion", "description"})
    private String description;

    @JsonAlias({"canciones", "songs"})
    private List<SongDto> songs;

    public PlaylistResponse() {}

    public PlaylistResponse(String name, String description, List<SongDto> songs) {
        this.name = name;
        this.description = description;
        this.songs = songs;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<SongDto> getSongs() { return songs; }

    public void setSongs(List<SongDto> songs) { this.songs = songs; }
}
