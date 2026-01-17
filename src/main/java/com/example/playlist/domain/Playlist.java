package com.example.playlist.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "playlists",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_playlists_name", columnNames = "name")
        }
)
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(
            mappedBy = "playlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Song> songs = new ArrayList<>();

    public Playlist() {}

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addSong(Song song) {
        if (song == null) return;
        songs.add(song);
        song.setPlaylist(this);
    }

    public void clearSongs() {
        for (Song s : songs) {
            s.setPlaylist(null);
        }
        songs.clear();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<Song> getSongs() { return songs; }

    public void setSongs(List<Song> songs) {
        this.songs = songs != null ? songs : new ArrayList<>();
    }
}
