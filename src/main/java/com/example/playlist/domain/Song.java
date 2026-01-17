package com.example.playlist.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false, length = 300)
    private String title;

    @Column(name="artist", length = 300)
    private String artist;

    @Column(name="album", length = 300)
    private String album;

    @Column(name="release_year")
    private Integer year;

    @Column(name="genre", length = 120)
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    public Song() {}

    public Song(String title, String artist, String album, Integer year, String genre) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }

    public Long getId() { return id; }

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

    public Playlist getPlaylist() { return playlist; }

    public void setPlaylist(Playlist playlist) { this.playlist = playlist; }
}
