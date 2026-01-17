package com.example.playlist.service;

import com.example.playlist.dto.playlist.CreatePlaylistRequest;
import com.example.playlist.exception.BadRequestException;
import com.example.playlist.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PlaylistService.class)
public class PlaylistServiceTest {

    @Autowired
    private PlaylistRepository repo;

    @Autowired
    private PlaylistService service;

    @BeforeEach
    void clean() {
        repo.deleteAll();
    }

    @Test
    void shouldCreatePlaylist() {
        CreatePlaylistRequest req = new CreatePlaylistRequest();
        req.setName("Lista 1");
        req.setDescription("Desc");

        var created = service.create(req);
        assertEquals("Lista 1", created.getName());
    }

    @Test
    void shouldFailOnDuplicate() {
        CreatePlaylistRequest req = new CreatePlaylistRequest();
        req.setName("Lista 1");

        service.create(req);

        CreatePlaylistRequest req2 = new CreatePlaylistRequest();
        req2.setName("Lista 1");

        assertThrows(BadRequestException.class, () -> service.create(req2));
    }
}
