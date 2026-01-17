package com.example.playlist.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    @Test
    void shouldGenerateAndParseSubject() {
        JwtService jwt = new JwtService("super-secret", 10);
        String token = jwt.generateToken("admin");
        assertNotNull(token);

        String subject = jwt.tryGetSubject(token);
        assertEquals("admin", subject);
    }

    @Test
    void shouldReturnNullForInvalidToken() {
        JwtService jwt = new JwtService("super-secret", 10);
        assertNull(jwt.tryGetSubject("invalid.token.value"));
    }
}
