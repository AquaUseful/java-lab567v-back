package org.psu.lab567.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.psu.lab567.auth.JwtAuth;
import org.psu.lab567.model.User;
import org.psu.lab567.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;

@Component
public class JwtUtils {

    private final SecretKey secret;
    private final int expirationSeconds;

    @Autowired
    private UserService userService;

    JwtUtils(
            @Value("${lab567v.jwtSecret}") String secret,
            @Value("${lab567v.jwtExpirationSeconds}") int expirationSeconds) {
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationSeconds = expirationSeconds;
    }

    public String generate(@NonNull User user) {
        final LocalDateTime timeNow = LocalDateTime.now();
        final Instant expirationInstant = timeNow.plusSeconds(this.expirationSeconds).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date expiration = Date.from(expirationInstant);
        return Jwts.builder()
                .setExpiration(expiration)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(this.secret)
                .compact();
    }

    public boolean validate(@NonNull String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(this.secret)
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | SignatureException e) {
            return false;
        }
    }

    public Claims extractClaims(@NonNull String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public long extractId(@NonNull Claims claims) {
        final long id = claims.get("id", Long.class);
        return id;
    }

    public JwtAuth generateAuth(@NonNull Claims claims) {
        final long id = this.extractId(claims);
        final User user = userService.getById(id);
        final JwtAuth auth = new JwtAuth(user);
        return auth;
    }

}
