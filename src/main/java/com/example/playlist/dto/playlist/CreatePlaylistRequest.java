package com.example.playlist.dto.playlist;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreatePlaylistRequest {

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @Valid
    private List<SongDto> songs;

    public CreatePlaylistRequest() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<SongDto> getSongs() { return songs; }

    public void setSongs(List<SongDto> songs) { this.songs = songs; }
}
