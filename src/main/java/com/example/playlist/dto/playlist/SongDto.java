package com.example.playlist.dto.playlist;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public class SongDto {

    @JsonAlias({"titulo", "title"})
    @NotBlank(message = "title is required")
    private String title;

    @JsonAlias({"artista", "artist"})
    private String artist;

    @JsonAlias({"album"})
    private String album;

    @JsonAlias({"anno", "year"})
    private Integer year;

    @JsonAlias({"genero", "genre"})
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
