package com.example.playlist.repository;

import com.example.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
