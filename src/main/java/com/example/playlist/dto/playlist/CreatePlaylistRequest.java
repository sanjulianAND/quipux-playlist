package com.example.playlist.dto.playlist;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreatePlaylistRequest {

    @NotBlank(message = "nombre is required")
    @JsonProperty("nombre")
    @JsonAlias({"name"})
    private String name;

    @JsonProperty("descripcion")
    @JsonAlias({"description"})
    private String description;

    @Valid
    @JsonProperty("canciones")
    @JsonAlias({"songs"})
    private List<SongDto> songs;

    public CreatePlaylistRequest() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<SongDto> getSongs() { return songs; }

    public void setSongs(List<SongDto> songs) { this.songs = songs; }
}
