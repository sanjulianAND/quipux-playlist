package com.example.playlist.dto.playlist;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class SongDto {

    @NotBlank(message = "titulo is required")
    @JsonProperty("titulo")
    @JsonAlias({"title"})
    private String title;

    @JsonProperty("artista")
    @JsonAlias({"artist"})
    private String artist;

    @JsonProperty("album")
    @JsonAlias({"album"})
    private String album;

    @JsonProperty("anno")
    @JsonAlias({"year"})
    private Integer year;

    @JsonProperty("genero")
    @JsonAlias({"genre"})
    private String genre;

    public SongDto() {}

    public SongDto(String title, String artist, String album, Integer year, String genre) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }

    public void setArtist(String artist) { this.artist = artist; }

    public String getAlbum() { return album; }

    public void setAlbum(String album) { this.album = album; }

    public Integer getYear() { return year; }

    public void setYear(Integer year) { this.year = year; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }
}
