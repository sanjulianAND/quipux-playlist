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

    @Test
    void shouldReturnNullWhenTokenIsTampered() {
        JwtService jwt = new JwtService("super-secret", 10);
        String token = jwt.generateToken("admin");
        assertNotNull(token);

        // Cambiar 1 caracter suele romper la firma
        String tampered = token.substring(0, token.length() - 2) + "aa";

        assertNull(jwt.tryGetSubject(tampered));
    }

    @Test
    void shouldReturnNullWhenTokenSignedWithDifferentSecret() {
        JwtService jwt1 = new JwtService("super-secret", 10);
        JwtService jwt2 = new JwtService("other-secret", 10);

        String token = jwt1.generateToken("admin");
        assertNotNull(token);

        assertNull(jwt2.tryGetSubject(token));
    }

    @Test
    void shouldReturnNullWhenTokenIsExpired() {
        // TTL 0: exp "ya"
        JwtService jwt = new JwtService("super-secret", 0);
        String token = jwt.generateToken("admin");
        assertNotNull(token);

        assertNull(jwt.tryGetSubject(token));
    }
}
